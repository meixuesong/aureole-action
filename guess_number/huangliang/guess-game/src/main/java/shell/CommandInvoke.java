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
        commands.put("start", new StartCommand(guessGame));
        commands.put("guess", new GuessCommand(guessGame));
        commands.put("quit", new QuitCommand(guessGame));
    }

    public Command getStartCommand() {

        return commands.get("start");
    }

    public Command getGuessCommand() {

        return commands.get("guess");
    }


    public Command getQuitCommand() {

        return commands.get("quit");
    }
}
