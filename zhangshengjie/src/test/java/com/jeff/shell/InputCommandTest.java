package com.jeff.shell;

import com.jeff.core.ConsoleService;
import com.jeff.core.State;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;


/**
 * Created by jeff on 7/25/16.
 */
public class InputCommandTest {
    @Mock
    private ConsoleService consoleService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(consoleService.getSubInput()).thenReturn("1234");
    }

    @Test
    public void invoke_guess() throws Exception {
        State state=new State("1234","1234","",2,null);

        new InputCommand(consoleService).invoke(state);

        assertThat(state.getNext() instanceof GuessCommand,is(true));
    }

    @Test
    public void invoke_exit() throws Exception {
        State state=new State(null,null,"",2,null);
        when(consoleService.getMainChoice()).thenReturn(1);

        new InputCommand(consoleService).invoke(state);

        assertThat(state.getNext() instanceof ExitCommand,is(true));
    }

    @Test
    public void invoke_start() throws Exception {
        State state=new State(null,null,"",2,null);
        when(consoleService.getMainChoice()).thenReturn(0);

        new InputCommand(consoleService).invoke(state);

        assertThat(state.getNext() instanceof StartCommand,is(true));
    }

}