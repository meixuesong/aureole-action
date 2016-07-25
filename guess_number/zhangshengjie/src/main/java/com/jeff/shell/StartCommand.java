package com.jeff.shell;

import com.jeff.core.RandomUtils;
import com.jeff.core.State;

/**
 * Created by jeff on 7/17/16.
 */
public class StartCommand implements Command {


    @Override
    public void invoke(State state) {
        state.reset();
        state.setSequence(RandomUtils.generateSequence());
        state.setNext(new InputCommand());
    }
}
