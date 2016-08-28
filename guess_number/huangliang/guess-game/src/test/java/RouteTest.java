import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import core.GuessGame;
import core.NumberMatcher;
import core.RandomNumberGenerator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import shell.CommandInvoke;
import shell.Route;

import java.io.BufferedReader;
import java.io.PrintStream;

/**
 * Created by lianghuang on 7/20/16.
 */
public class RouteTest {

    private RandomNumberGenerator randomNumberGenerator;
    private GuessGame game;
    private PrintStream out;
    private BufferedReader reader;
    private CommandInvoke commandInvoke;
    private Route route;

    @Before
    public void befor() throws Exception {
        randomNumberGenerator = mock(RandomNumberGenerator.class);
        out = mock(PrintStream.class);
        reader = mock(BufferedReader.class);
        game = new GuessGame(out, reader, new NumberMatcher(), randomNumberGenerator);
        commandInvoke = new CommandInvoke(game, out);
        route = new Route(out, game, commandInvoke);
        given(reader.readLine()).willReturn("1234");
        given(randomNumberGenerator.generateAnswer()).willReturn("4321");
    }

    @Test
    public void shoud_start_a_game() {

        route.route("1");

        InOrder inOrder = inOrder(out);
        inOrder.verify(out).printf("Please input 1 to start a new game or input 2 quit the game:");
        inOrder.verify(out).println("Welcome");
    }


    @Test
    public void shoud_exit_a_game() {

        route.route("2");

        InOrder inOrder = inOrder(out);
        inOrder.verify(out).printf("Please input 1 to start a new game or input 2 quit the game:");
        inOrder.verify(out).println("Good bye!");
    }


}
