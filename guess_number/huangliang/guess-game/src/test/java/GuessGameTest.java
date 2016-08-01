import static org.fest.assertions.api.Assertions.assertThat;

import core.GameMessage;
import core.Player;
import core.NumberMatcher;
import core.RandomNumberGenerator;
import core.GuessGame;
import core.StatusEnum;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by lianghuang on 7/17/16.
 */
public class GuessGameTest {

    @Test
    public void should_be_initialized(){
        GuessGame guessGame = new GuessGame(new Player(), new NumberMatcher(), new RandomNumberGenerator());
        guessGame.start();

        assertThat(guessGame.getPlayer().getLifeValue()).isEqualTo(6);
        assertThat(guessGame.getPlayer().getStatus()).isEqualTo(StatusEnum.playing);
    }




    @Test
    public void should_return_null_when_validate_1234() {
        GuessGame guessGame = new GuessGame(new Player(), new NumberMatcher(), new RandomNumberGenerator());

        String errorMessage = guessGame.validate("1234");
        assertThat(errorMessage == null);
    }


    @Test
    public void should_return_error_message_when_validate_abcd() {
        GuessGame guessGame = new GuessGame(new Player(), new NumberMatcher(), new RandomNumberGenerator());
        String errorMessage = guessGame.validate("abcd");
        assertThat(errorMessage).isEqualTo(GuessGame.ERROR_MESSAGE);


    }

    @Test
    public void should_return_error_message_when_validate_abc() {
        GuessGame guessGame = new GuessGame(new Player(), new NumberMatcher(), new RandomNumberGenerator());
        String errorMessage = guessGame.validate("abc");
        assertThat(errorMessage).isEqualTo(GuessGame.ERROR_MESSAGE);
    }


    @Test
    public void should_return_error_message_when_validate_1() {
        GuessGame guessGame = new GuessGame(new Player(),new NumberMatcher(),  new RandomNumberGenerator());
        String errorMessage = guessGame.validate("1");
        assertThat(errorMessage).isEqualTo(GuessGame.ERROR_MESSAGE);
    }


    @Test
    public void should_return_error_message_when_validate_null() {
        GuessGame guessGame = new GuessGame(new Player(),new NumberMatcher(),  new RandomNumberGenerator());
        String errorMessage = guessGame.validate(null);
        assertThat(errorMessage).isEqualTo(GuessGame.ERROR_MESSAGE);
    }

    @Test
    public void should_return_error_message_when_validate_1123() {
        GuessGame guessGame = new GuessGame(new Player(), new NumberMatcher(), new RandomNumberGenerator());
        String errorMessage = guessGame.validate("1123");
        assertThat(errorMessage).isEqualTo(GuessGame.ERROR_MESSAGE);
    }



    @Test
    public void should_start_a_new_game() {

        GuessGame guessGame = new GuessGame(new Player(), new NumberMatcher(), new RandomNumberGenerator());
        guessGame.start();

        assertThat(guessGame.getPlayer().getLifeValue()).isEqualTo(6);
        assertThat(guessGame.getPlayer().getStatus()).isEqualTo(StatusEnum.playing);
    }

    @Test
    public void should_keep_a_ongoing_game() {
        Player player = mock(Player.class);
        when(player.getLifeValue()).thenReturn(4);
        when(player.getStatus()).thenReturn(StatusEnum.playing);
        GuessGame guessGame = new GuessGame(player, new NumberMatcher(), new RandomNumberGenerator());

        GameMessage gameMessage = guessGame.start();

        assertThat(guessGame.getPlayer().getLifeValue()).isEqualTo(4);
        assertThat(guessGame.getPlayer().getStatus()).isEqualTo(StatusEnum.playing);
        assertThat(gameMessage.getErrorMessage() != null);
    }

    @Test
    public void should_return_error_info() {
        GuessGame guessGame = new GuessGame(new Player(), new NumberMatcher(), new RandomNumberGenerator());
        guessGame.start();

        GameMessage gameMessage = guessGame.guess("1A23");

        assertThat(gameMessage.getErrorMessage()).isEqualTo(GuessGame.ERROR_MESSAGE);
    }

    @Test
    public void should_return_normal_status() {
        RandomNumberGenerator randomNumberGenerator = mock(RandomNumberGenerator.class);
        when(randomNumberGenerator.generateAnswer()).thenReturn("1234");
        GuessGame guessGame = new GuessGame(new Player(), new NumberMatcher(), randomNumberGenerator);
        guessGame.start();

        GameMessage gameMessage = guessGame.guess("1357");

        assertThat(gameMessage.getLifeValue()).isEqualTo(5);
        assertThat(gameMessage.getStatusEnum()).isEqualTo(StatusEnum.playing);
        assertThat(gameMessage.getCalculateResult()).isEqualTo("1A1B");

    }

    @Test
    public void should_return_failure_status() {
        Player player = mock(Player.class);
        when(player.getLifeValue()).thenReturn(1);
        when(player.getStatus()).thenReturn(StatusEnum.playing);
        RandomNumberGenerator randomNumberGenerator = mock(RandomNumberGenerator.class);
        when(randomNumberGenerator.generateAnswer()).thenReturn("1234");
        GuessGame guessGame = new GuessGame(player, new NumberMatcher(), randomNumberGenerator);

        GameMessage gameMessage = guessGame.guess("1357");

        assertThat(gameMessage.getLifeValue()).isEqualTo(0);
        assertThat(gameMessage.getStatusEnum()).isEqualTo(StatusEnum.failure);
        assertThat(gameMessage.getCalculateResult()).isEqualTo("1A1B");
        assertThat(gameMessage.getErrorMessage()).isEqualTo(GuessGame.ERROR_GAME_OVER);

    }

    @Test
    public void should_return_success_status() {
        Player player = mock(Player.class);
        when(player.getLifeValue()).thenReturn(1);
        when(player.getStatus()).thenReturn(StatusEnum.playing);
        RandomNumberGenerator randomNumberGenerator = mock(RandomNumberGenerator.class);
        when(randomNumberGenerator.generateAnswer()).thenReturn("1234");
        GuessGame guessGame = new GuessGame(player, new NumberMatcher(), randomNumberGenerator);


        GameMessage gameMessage = guessGame.guess("1234");

        assertThat(gameMessage.getLifeValue()).isEqualTo(0);
        assertThat(gameMessage.getStatusEnum()).isEqualTo(StatusEnum.win);
        assertThat(gameMessage.getCalculateResult()).isEqualTo("4A0B");
        assertThat(gameMessage.getErrorMessage()).isEqualTo(GuessGame.GAME_WIN);

    }


}
