package com.jeff.shell;

import com.jeff.core.State;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by jeff on 7/17/16.
 */
public class StartCommandTest {
    @Test
    public void invoke() throws Exception {
        State state=new State();

        new StartCommand().invoke(state);

        assertThat(state.getSequence().length(),is(4));
        assertThat(state.getNext() instanceof InputCommand,is(true));
    }

}