package shell;

import core.GameMessage;

/**
 * Created by lianghuang on 7/20/16.
 */
public interface Command {
    GameMessage execute(String parameters);
}
