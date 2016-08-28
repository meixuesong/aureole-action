package shell;

import core.GameMessage;
import core.GuessGame;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by lianghuang on 7/20/16.
 */
public class StartCommand extends Command {

    public StartCommand(GuessGame guessGame, PrintStream out) {
        super(guessGame, out);
    }

    public void execute() {
        try {
            out.println("Welcome");
            guessGame.start();
            out.printf("Please input 1 to start a new game or input 2 quit the game:");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
