package com.jeff;

import com.jeff.core.State;
import com.jeff.shell.InputCommand;

/**
 * Created by jeff on 7/17/16.
 */
public class Main {
    public static void main(String[] strs){
        State state=new State();
        new InputCommand().invoke(state);
        while(state.getNext()!=null){
            state.getNext().invoke(state);
        }
    }

}
