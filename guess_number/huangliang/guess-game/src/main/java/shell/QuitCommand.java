package shell;

import static java.lang.System.exit;

import core.GameMessage;
import core.GuessGame;

import java.io.PrintStream;

/**
 * Created by lianghuang on 7/20/16.
 */
public class QuitCommand extends Command {

    public QuitCommand(GuessGame guessGame, PrintStream out) {
        super(guessGame, out);
    }

    public void execute() {
        out.println("Good bye!");
    }
}
