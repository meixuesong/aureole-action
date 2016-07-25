package shell;

import static java.lang.System.exit;

import core.GameMessage;

/**
 * Created by lianghuang on 7/20/16.
 */
public class QuitCommand implements Command {
    public GameMessage execute(String parameters) {
        exit(0);
        return null;
    }
}
