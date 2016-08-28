package shell;

import core.GameMessage;
import core.GuessGame;

import java.io.PrintStream;

/**
 * Created by lianghuang on 7/20/16.
 */
public abstract class Command {
    protected GuessGame guessGame;
    protected PrintStream out;

    public Command(GuessGame guessGame, PrintStream out) {
        this.guessGame = guessGame;
        this.out = out;
    }

    abstract void execute();
}
