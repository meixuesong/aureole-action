package shell;

import core.GameMessage;
import core.GuessGame;
import core.StatusEnum;

import java.io.PrintStream;

/**
 * Created by lianghuang on 7/20/16.
 */
public class Route {

    //TODO: using new GuessGame instance when start new game.
    private GuessGame guessGame;
    private CommandInvoke commandInvoke;
    private PrintStream out;

    public Route(PrintStream out, GuessGame guessGame, CommandInvoke commandInvoke) {
        this.out = out;
        this.commandInvoke = commandInvoke;
        this.guessGame = guessGame;

        this.out.printf("Please input 1 to start a new game or input 2 quit the game:");
    }

    public void route(String input) {
        commandInvoke.getCommand(input).execute();
    }


}
