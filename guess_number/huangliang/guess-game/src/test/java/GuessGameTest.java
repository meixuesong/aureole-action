import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import core.GuessGame;
import core.NumberMatcher;
import core.RandomNumberGenerator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by lianghuang on 7/17/16.
 */
public class GuessGameTest {

    private RandomNumberGenerator randomNumberGenerator;
    private GuessGame game;
    private PrintStream out;
    private BufferedReader reader;

    @Before
    public void befor() throws Exception {
        randomNumberGenerator = mock(RandomNumberGenerator.class);
        out = mock(PrintStream.class);
        reader = mock(BufferedReader.class);
        game = new GuessGame(out, reader, new NumberMatcher(), randomNumberGenerator);

        given(reader.readLine()).willReturn("1234");
        given(randomNumberGenerator.generateAnswer()).willReturn("4321");

    }

    @Test
    public void should_print_please_input_after_game_start() throws IOException {
        game.start();
        InOrder inOrder = inOrder(out);
        inOrder.verify(out).printf("Please input your number(6): ");
    }


    @Test
    public void should_reduce_one_chance_when_guess_wrong() throws IOException {
        game.start();
        InOrder inOrder = inOrder(out);
        inOrder.verify(out).printf("Please input your number(6): ");
        inOrder.verify(out).println("0A4B");
        inOrder.verify(out).printf("Please input your number(5): ");
    }

    @Test
    public void should_reduce_chances_one_by_one_until_game_over() throws IOException {
        game.start();
        InOrder inOrder = inOrder(out);
        inOrder.verify(out).printf("Please input your number(6): ");
        inOrder.verify(out).println("0A4B");
        inOrder.verify(out).printf("Please input your number(5): ");
        inOrder.verify(out).println("0A4B");
        inOrder.verify(out).printf("Please input your number(4): ");
        inOrder.verify(out).println("0A4B");
        inOrder.verify(out).printf("Please input your number(3): ");
        inOrder.verify(out).println("0A4B");
        inOrder.verify(out).printf("Please input your number(2): ");
        inOrder.verify(out).println("0A4B");
        inOrder.verify(out).printf("Please input your number(1): ");
        inOrder.verify(out).println("0A4B");
        inOrder.verify(out).println("Game Over");
    }


    @Test
    public void should_congratulate_when_input_is_right() throws IOException {
        given(reader.readLine()).willReturn("4321");
        game.start();
        InOrder inOrder = inOrder(out);
        inOrder.verify(out).printf("Please input your number(6): ");
        inOrder.verify(out, never()).println("4A0B");
        inOrder.verify(out).println("Congratulate!!");
    }


    @Test
    public void should_congratulate_when_input_is_right_on_second_round() throws IOException {
        given(reader.readLine()).willReturn("1234").willReturn("4321");
        game.start();
        InOrder inOrder = inOrder(out);
        inOrder.verify(out).printf("Please input your number(6): ");
        inOrder.verify(out).println("0A4B");
        inOrder.verify(out).printf("Please input your number(5): ");
        inOrder.verify(out, never()).println("4A0B");
        inOrder.verify(out).println("Congratulate!!");
    }


    @Test
    public void should_congratulate_when_input_is_right_on_last_round() throws IOException {
        given(reader.readLine()).willReturn("1234", "1234", "1234", "1234", "1234").willReturn("4321");
        game.start();
        InOrder inOrder = inOrder(out);
        inOrder.verify(out).printf("Please input your number(6): ");
        inOrder.verify(out).println("0A4B");
        inOrder.verify(out).printf("Please input your number(5): ");
        inOrder.verify(out).println("0A4B");
        inOrder.verify(out).printf("Please input your number(4): ");
        inOrder.verify(out).println("0A4B");
        inOrder.verify(out).printf("Please input your number(3): ");
        inOrder.verify(out).println("0A4B");
        inOrder.verify(out).printf("Please input your number(2): ");
        inOrder.verify(out).println("0A4B");
        inOrder.verify(out).printf("Please input your number(1): ");
        inOrder.verify(out, never()).println("4A0B");
        inOrder.verify(out).println("Congratulate!!");
    }


    @Test
    public void should_show_the_error_message_when_input_invalid_string() throws IOException {
        given(reader.readLine()).willReturn("ABCD").willReturn("4321");
        game.start();
        InOrder inOrder = inOrder(out);
        inOrder.verify(out).printf("Please input your number(6): ");
        inOrder.verify(out).println("The guess number must be 4 different digits.");
        inOrder.verify(out).printf("Please input your number(6): ");
        inOrder.verify(out, never()).println("4A0B");
        inOrder.verify(out).println("Congratulate!!");
    }
}
