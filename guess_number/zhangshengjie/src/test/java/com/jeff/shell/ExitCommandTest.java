package com.jeff.shell;

import com.jeff.core.State;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by jeff on 7/17/16.
 */
public class ExitCommandTest {
    @Test
    public void should_be_exited() {
        State state=new State();
        state.setNext(new InputCommand());

        new ExitCommand().invoke(state);

        assertThat(state.getNext()==null,is(true));
    }
}