package main;

import command.Command;
import enumeration.Status;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import vo.CommandResponse;

/**
 * Created by zbwang on 16/7/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class RouterTest {

    @Mock
    CommandFactory commandFactory;

    @Mock
    Command command;

    @InjectMocks
    Router router;

    @Test
    public void should_return_currect_command_when_route_a_command(){
        String commandStr = "PrintStartTipsCommand";
        Mockito.when(command.execute(Matchers.anyString())).thenReturn(new
            CommandResponse(commandStr, Status.start));
        Mockito.when(commandFactory.get(commandStr)).thenReturn(command);

        Assert.assertThat(router.route(commandStr), CoreMatchers.is(commandStr));
    }

}