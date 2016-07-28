import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import core.GameMessage;
import core.GuessGame;
import core.GuessGameContext;
import core.RandomNumberGenerator;
import core.StatusEnum;
import org.junit.Test;
import shell.CommandInvoke;
import shell.Route;

import java.lang.reflect.Field;

/**
 * Created by lianghuang on 7/20/16.
 */
public class RouteTest {
    @Test
    public void shoud_start_a_game() {

        GuessGame guessGame = new GuessGame(new GuessGameContext(), new RandomNumberGenerator());
        CommandInvoke commandInvoke = new CommandInvoke(guessGame);

        GameMessage gameMessage = new Route(guessGame, commandInvoke).route("1");

        assertThat(gameMessage.getLifeValue()).isEqualTo(6);
        assertThat(gameMessage.getStatusEnum()).isEqualTo(StatusEnum.playing);
    }

    @Test
    public void shoud_reduce_life_value_to_5() {

        GuessGameContext guessGameContext = mock(GuessGameContext.class);
        when(guessGameContext.getLifeValue()).thenReturn(5);
        when(guessGameContext.getStatus()).thenReturn(StatusEnum.playing);
        when(guessGameContext.getAnswer()).thenReturn("1234");

        GuessGame guessGame = new GuessGame(guessGameContext, new RandomNumberGenerator());
        CommandInvoke commandInvoke = new CommandInvoke(guessGame);
        GameMessage gameMessage = new Route(guessGame, commandInvoke).route(null);

        assertThat(gameMessage.getLifeValue()).isEqualTo(5);
        assertThat(gameMessage.getStatusEnum()).isEqualTo(StatusEnum.playing);
        assertThat(gameMessage.getCalculateResult() == null);
        assertThat(gameMessage.getErrorMessage()).isEqualTo(GuessGame.ERROR_MESSAGE);
    }


    @Test
    public void shoud_win_the_game() {

        GuessGameContext guessGameContext = mock(GuessGameContext.class);
        when(guessGameContext.getLifeValue()).thenReturn(5);
        when(guessGameContext.getStatus()).thenReturn(StatusEnum.playing);
        when(guessGameContext.getAnswer()).thenReturn("1234");

        GuessGame guessGame = new GuessGame(guessGameContext, new RandomNumberGenerator());
        CommandInvoke commandInvoke = new CommandInvoke(guessGame);
        GameMessage gameMessage = new Route(guessGame, commandInvoke).route("1234");

        assertThat(gameMessage.getLifeValue()).isEqualTo(4);
        assertThat(gameMessage.getStatusEnum()).isEqualTo(StatusEnum.win);
        assertThat(gameMessage.getCalculateResult()).isEqualTo("4A0B");
    }


    @Test
    public void shoud_lose_the_game() {

        GuessGameContext guessGameContext = mock(GuessGameContext.class);
        when(guessGameContext.getLifeValue()).thenReturn(1);
        when(guessGameContext.getStatus()).thenReturn(StatusEnum.playing);
        when(guessGameContext.getAnswer()).thenReturn("1234");

        GuessGame guessGame = new GuessGame(guessGameContext, new RandomNumberGenerator());
        CommandInvoke commandInvoke = new CommandInvoke(guessGame);
        GameMessage gameMessage = new Route(guessGame, commandInvoke).route("1357");

        assertThat(gameMessage.getLifeValue()).isEqualTo(0);
        assertThat(gameMessage.getStatusEnum()).isEqualTo(StatusEnum.failure);
        assertThat(gameMessage.getCalculateResult()).isEqualTo("1A1B");
        assertThat(gameMessage.getErrorMessage()).isEqualTo(GuessGame.ERROR_GAME_OVER);

    }


    @Test
    public void shoud_reduce_the_life_value() {

        GuessGameContext guessGameContext = mock(GuessGameContext.class);
        when(guessGameContext.getLifeValue()).thenReturn(4);
        when(guessGameContext.getStatus()).thenReturn(StatusEnum.playing);
        when(guessGameContext.getAnswer()).thenReturn("1234");

        GuessGame guessGame = new GuessGame(guessGameContext, new RandomNumberGenerator());
        CommandInvoke commandInvoke = new CommandInvoke(guessGame);
        GameMessage gameMessage = new Route(guessGame, commandInvoke).route("1357");

        assertThat(gameMessage.getLifeValue()).isEqualTo(3);
    }

}
