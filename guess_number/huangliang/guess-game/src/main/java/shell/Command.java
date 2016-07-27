package shell;

import core.GameMessage;
import core.GuessGame;

/**
 * Created by lianghuang on 7/20/16.
 */
public abstract class Command {
    protected GuessGame guessGame;

    public Command(GuessGame guessGame) {
        this.guessGame = guessGame;
    }

    abstract GameMessage execute(String parameters);
}
