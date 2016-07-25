package com.jeff.shell;

import com.jeff.core.State;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by jeff on 7/17/16.
 */
public class GuessCommandTest {
    @Test
    public void invoke_success() throws Exception {
        State state=new State("1234","1234","",2,null);
        new GuessCommand().invoke(state);

        assertThat(state.getOutput(),is("4A0B"));
        assertThat(state.getResult().equals("Congratulation"),is(true));
    }

    @Test
    public void same_a_no_b() throws Exception {
        State state=new State("1234","1235","",2,null);
        new GuessCommand().invoke(state);

        assertThat(state.getOutput(),is("3A0B"));
        assertThat(state.getResult(),is("ONGOING"));
    }

    @Test
    public void no_a_no_b() throws Exception {
        State state=new State("1234","6789","",2,null);
        new GuessCommand().invoke(state);

        assertThat(state.getOutput(),is("0A0B"));
        assertThat(state.getResult(),is("ONGOING"));
    }

    @Test
    public void no_a_some_b() throws Exception {
        State state=new State("1234","4321","",2,null);
        new GuessCommand().invoke(state);

        assertThat(state.getOutput(),is("0A4B"));
        assertThat(state.getResult(),is("ONGOING"));
    }

    @Test
    public void game_over() throws Exception {
        State state=new State("1234","4321","",1,null);
        new GuessCommand().invoke(state);

        assertThat(state.getOutput(),is("0A4B"));
        assertThat(state.getResult(),is("GameOver"));
    }
}