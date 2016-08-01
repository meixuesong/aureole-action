package shell;

import core.GameMessage;
import core.GuessGame;

/**
 * Created by lianghuang on 7/20/16.
 */
public class StartCommand extends Command {

    public final static String CommandName = "start";

    public StartCommand(GuessGame guessGame) {
        super(guessGame);
    }

    public GameMessage execute(String parameters) {
        return guessGame.start();
    }
}
