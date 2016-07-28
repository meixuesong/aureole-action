package shell;

import core.GuessGame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lianghuang on 7/20/16.
 */
public class CommandInvoke {

    private Map<String, Command> commands;

    public CommandInvoke(GuessGame guessGame) {
        commands = new HashMap<String, Command>();
        commands.put(StartCommand.CommandName, new StartCommand(guessGame));
        commands.put(GuessCommand.CommandName, new GuessCommand(guessGame));
        commands.put(QuitCommand.CommandName, new QuitCommand(guessGame));
    }

    public Command invoke(String commandName) {

        return commands.get(commandName);
    }
}
