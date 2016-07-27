package shell;

import core.GuessGame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lianghuang on 7/20/16.
 */
public class CommandInvoke {

    private Map<CommandEnum, Command> commands;

    public CommandInvoke(GuessGame guessGame) {
        commands = new HashMap<CommandEnum, Command>();
        commands.put(CommandEnum.start, new StartCommand(guessGame));
        commands.put(CommandEnum.guess, new GuessCommand(guessGame));
        commands.put(CommandEnum.quit, new QuitCommand(guessGame));
    }

    public Command invoke(CommandEnum commandEnum) {

        return commands.get(commandEnum);
    }
}
