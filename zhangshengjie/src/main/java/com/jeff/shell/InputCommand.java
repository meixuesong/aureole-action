package com.jeff.shell;

import com.jeff.core.ConsoleService;
import com.jeff.core.State;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by jeff on 7/17/16.
 */
public class InputCommand implements Command {
    private static final String MAIN_MENU = "Please input your choice:";
    private static final String SUB_MENU = "Please input your guess(length=4):";

    private ConsoleService consoleService;

    public InputCommand(ConsoleService consoleService){
        this.consoleService=consoleService;
    }

    public InputCommand(){
        this(new ConsoleService());
    }

    @Override
    public void invoke(State state) {
        if (state.getSequence() == null) {
            this.mainMenu(state);
        } else {
            this.subMenu(state);
        }
    }


    private void subMenu(State state) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(SUB_MENU);

        String s = consoleService.getSubInput();
        state.setInput(s);
        state.setNext(new GuessCommand());
    }

    private void mainMenu(State state) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(MAIN_MENU);

        int i = consoleService.getMainChoice();
        if (i == 0) {
            state.setNext(new StartCommand());
        } else {
            state.setNext(new ExitCommand());
        }
    }
}
