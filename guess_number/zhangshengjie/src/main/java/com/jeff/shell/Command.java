package com.jeff.shell;

import com.jeff.core.State;

/**
 * Created by jeff on 7/17/16.
 */
public interface Command {

    void invoke(State state);
}
