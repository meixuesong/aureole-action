package shell;

import core.GameMessage;
import core.GuessGame;

/**
 * Created by lianghuang on 7/20/16.
 */
public class StartCommand implements Command {
    public GameMessage execute(String parameters) {
        return new GuessGame().startNew();
    }
}
