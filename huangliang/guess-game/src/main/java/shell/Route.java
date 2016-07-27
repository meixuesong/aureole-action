package shell;

import core.GameMessage;

/**
 * Created by lianghuang on 7/20/16.
 */
public class Route {


    public GameMessage route(CommandEnum commandEnum, String guessValue) {
        CommandInvoke commandInvoke = new CommandInvoke();

        Command command = commandInvoke.invoke(commandEnum);

        return command.execute(guessValue);
    }
}
