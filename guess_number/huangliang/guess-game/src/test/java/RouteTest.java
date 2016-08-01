import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import core.GameMessage;
import core.GuessGame;
import core.Player;
import core.NumberMatcher;
import core.RandomNumberGenerator;
import core.StatusEnum;
import org.junit.Test;
import shell.CommandInvoke;
import shell.Route;

/**
 * Created by lianghuang on 7/20/16.
 */
public class RouteTest {
    @Test
    public void shoud_start_a_game() {

        GuessGame guessGame = new GuessGame(new Player(), new NumberMatcher(), new RandomNumberGenerator());
        CommandInvoke commandInvoke = new CommandInvoke(guessGame);

        GameMessage gameMessage = new Route(guessGame, commandInvoke).route("1");

        assertThat(gameMessage.getLifeValue()).isEqualTo(6);
        assertThat(gameMessage.getStatusEnum()).isEqualTo(StatusEnum.playing);
    }

    @Test
    public void shoud_reduce_life_value_to_5() {

        Player player = mock(Player.class);
        when(player.getLifeValue()).thenReturn(5);
        when(player.getStatus()).thenReturn(StatusEnum.playing);
        RandomNumberGenerator randomNumberGenerator = mock(RandomNumberGenerator.class);
        when(randomNumberGenerator.generateAnswer()).thenReturn("1234");
        GuessGame guessGame = new GuessGame(player, new NumberMatcher(), randomNumberGenerator);

        CommandInvoke commandInvoke = new CommandInvoke(guessGame);
        GameMessage gameMessage = new Route(guessGame, commandInvoke).route(null);

        assertThat(gameMessage.getLifeValue()).isEqualTo(5);
        assertThat(gameMessage.getStatusEnum()).isEqualTo(StatusEnum.playing);
        assertThat(gameMessage.getCalculateResult() == null);
        assertThat(gameMessage.getErrorMessage()).isEqualTo(GuessGame.ERROR_MESSAGE);
    }


    @Test
    public void shoud_win_the_game() {

        Player player = mock(Player.class);
        when(player.getLifeValue()).thenReturn(5);
        when(player.getStatus()).thenReturn(StatusEnum.playing);
        when(player.reduceLifeValue()).thenReturn(4);
        RandomNumberGenerator randomNumberGenerator = mock(RandomNumberGenerator.class);
        when(randomNumberGenerator.generateAnswer()).thenReturn("1234");
        GuessGame guessGame = new GuessGame(player, new NumberMatcher(), randomNumberGenerator);

        CommandInvoke commandInvoke = new CommandInvoke(guessGame);
        GameMessage gameMessage = new Route(guessGame, commandInvoke).route("1234");

        assertThat(gameMessage.getLifeValue()).isEqualTo(4);
        assertThat(gameMessage.getStatusEnum()).isEqualTo(StatusEnum.win);
        assertThat(gameMessage.getCalculateResult()).isEqualTo("4A0B");
    }


    @Test
    public void shoud_lose_the_game() {

        Player player = mock(Player.class);
        when(player.getLifeValue()).thenReturn(1);
        when(player.getStatus()).thenReturn(StatusEnum.playing);
        RandomNumberGenerator randomNumberGenerator = mock(RandomNumberGenerator.class);
        when(randomNumberGenerator.generateAnswer()).thenReturn("1234");
        GuessGame guessGame = new GuessGame(player, new NumberMatcher(), randomNumberGenerator);

        CommandInvoke commandInvoke = new CommandInvoke(guessGame);
        GameMessage gameMessage = new Route(guessGame, commandInvoke).route("1357");

        assertThat(gameMessage.getLifeValue()).isEqualTo(0);
        assertThat(gameMessage.getStatusEnum()).isEqualTo(StatusEnum.failure);
        assertThat(gameMessage.getCalculateResult()).isEqualTo("1A1B");
        assertThat(gameMessage.getErrorMessage()).isEqualTo(GuessGame.ERROR_GAME_OVER);

    }

}
