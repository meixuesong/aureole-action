package com.jeff.shell;

import com.jeff.core.GuessService;
import com.jeff.core.State;

/**
 * Created by jeff on 7/17/16.
 */
public class GuessCommand implements Command {
    private GuessService guessService=new GuessService();
    private static final String SUCESS="Congratulation";
    private static final String FAILED="GameOver";

    @Override
    public void invoke(State state) {
        String tips= guessService.tryGuess(state.getSequence(),state.getInput());
        System.out.println(tips);
        if(tips.equalsIgnoreCase("4a0b")){
            System.out.println(SUCESS);
            state.reset();
            state.setResult(SUCESS);
        } else {
            state.decCount();
            if(state.gameOver()){
                System.out.println(FAILED);
                state.reset();
                state.setResult(FAILED);
            } else {
                state.setResult("ONGOING");
            }
        }
        state.setOutput(tips);
        state.setNext(new InputCommand());
    }

}
