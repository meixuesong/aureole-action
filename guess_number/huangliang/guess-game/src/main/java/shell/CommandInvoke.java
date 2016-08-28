package shell;

import core.GuessGame;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lianghuang on 7/20/16.
 */
public class CommandInvoke {

    private Map<String, Command> commands = new HashMap<>();

    public CommandInvoke(GuessGame guessGame, PrintStream out) {
        commands.put("1", new StartCommand(guessGame, out));
        commands.put("2", new QuitCommand(guessGame, out));
    }

    public Command getCommand(String command) {
        return commands.get(command);
    }
}
