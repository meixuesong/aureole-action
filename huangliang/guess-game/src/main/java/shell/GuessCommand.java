package shell;

import core.GameMessage;
import core.GuessGame;

/**
 * Created by lianghuang on 7/20/16.
 */
public class GuessCommand implements Command {
    public GameMessage execute(String parameters) {
        return new GuessGame().calculate(parameters);
    }
}
