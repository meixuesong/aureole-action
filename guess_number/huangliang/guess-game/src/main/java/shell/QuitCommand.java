package shell;

import static java.lang.System.exit;

import core.GameMessage;
import core.GuessGame;

/**
 * Created by lianghuang on 7/20/16.
 */
public class QuitCommand extends Command {

    public QuitCommand(GuessGame guessGame) {
        super(guessGame);
    }

    public GameMessage execute(String parameters) {
        exit(0);
        return null;
    }
}
