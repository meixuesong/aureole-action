package command;

import core.CoreServices;
import enumeration.CodeType;
import enumeration.Status;
import main.CommandResponseFactory;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import vo.CommandResponse;

/**
 * Created by zbwang on 16/7/25.
 */
@RunWith(MockitoJUnitRunner.class)
public class ZipToBarCommandTest {

    @Mock
    CoreServices coreServices;

    @Before
    public void setUp(){
        Mockito.when(coreServices.translation(Matchers.anyString(), Matchers.eq(CodeType.ZIP))).thenReturn("123");
    }

    @Test
    public void should_return_correct_status_and_message_when_translate_barcode(){
        ZipToBarCommand zipToBarCommand = new ZipToBarCommand(new CommandResponseFactory(), coreServices);
        CommandResponse cr = zipToBarCommand.execute("ddd");
        Assert.assertThat(cr.getMessage(), CoreMatchers.is("123"));
        Assert.assertThat(cr.getCurrentStatus(), CoreMatchers.is(Status.zipToBar));
    }
}