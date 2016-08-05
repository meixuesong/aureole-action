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

    private GuessCommand guessCommand = null;

    public CommandInvoke(GuessGame guessGame) {
        commands = new HashMap<String, Command>();
        commands.put("1", new StartCommand(guessGame));
        commands.put("2", new QuitCommand(guessGame));

        guessCommand = new GuessCommand(guessGame);
    }

    public Command getCommand(boolean isStarted, String command) {

        if (!isStarted) {
            return commands.get(command);
        } else {
            return  guessCommand;
        }
    }
}
