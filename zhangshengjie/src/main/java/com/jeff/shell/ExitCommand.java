package com.jeff.shell;

import com.jeff.core.State;

/**
 * Created by jeff on 7/17/16.
 */
public class ExitCommand implements Command {

    @Override
    public void invoke(State state) {
        state.setNext(null);
    }
}
