import core.GuessGame;
import core.NumberMatcher;
import core.RandomNumberGenerator;
import shell.CommandInvoke;
import shell.Route;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by lianghuang on 7/23/16.
 */
public class Application {


    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        GuessGame guessGame = new GuessGame(System.out,
            bufferedReader, new NumberMatcher(), new RandomNumberGenerator());

        CommandInvoke commandInvoke = new CommandInvoke(guessGame, System.out);
        Route route = new Route(System.out, guessGame, commandInvoke);

        String input = null;
        while (!"2".equals(input)) {
            try {
                input = bufferedReader.readLine();
                route.route(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
