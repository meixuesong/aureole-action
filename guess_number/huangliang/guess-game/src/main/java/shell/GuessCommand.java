package shell;

import core.GameMessage;
import core.GuessGame;

/**
 * Created by lianghuang on 7/20/16.
 */
public class GuessCommand extends Command {

    public final static String CommandName = "guess";

    public GuessCommand(GuessGame guessGame) {
        super(guessGame);
    }

    public GameMessage execute(String parameters) {
        return guessGame.calculate(parameters);
    }
}
