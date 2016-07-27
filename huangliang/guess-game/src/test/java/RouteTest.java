import static org.fest.assertions.api.Assertions.assertThat;

import core.GameMessage;
import core.GuessGame;
import core.SessionStorage;
import core.StatusEnum;
import org.junit.Test;
import shell.CommandEnum;
import shell.Route;

/**
 * Created by lianghuang on 7/20/16.
 */
public class RouteTest {
    @Test
    public void shoud_start_a_game() {
        SessionStorage.put("status", "0");

        GameMessage gameMessage = new Route().route(CommandEnum.start, null);

        assertThat(gameMessage.getLifeValue()).isEqualTo(6);
        assertThat(gameMessage.getStatusEnum()).isEqualTo(StatusEnum.playing);
    }

    @Test
    public void shoud_reduce_life_value_to_5() {

        SessionStorage.put("lifeValue", "5");
        SessionStorage.put("status", "1");
        SessionStorage.put("answer", "1234");

        GameMessage gameMessage = new Route().route(CommandEnum.guess, null);

        assertThat(gameMessage.getLifeValue()).isEqualTo(5);
        assertThat(gameMessage.getStatusEnum()).isEqualTo(StatusEnum.playing);
        assertThat(gameMessage.getCalculateResult() == null);
        assertThat(gameMessage.getErrorMessage()).isEqualTo(GuessGame.ERROR_MESSAGE);

    }


    @Test
    public void shoud_win_the_game() {

        SessionStorage.put("lifeValue", "5");
        SessionStorage.put("status", "1");
        SessionStorage.put("answer", "1234");

        GameMessage gameMessage = new Route().route(CommandEnum.guess, "1234");

        assertThat(gameMessage.getLifeValue()).isEqualTo(4);
        assertThat(gameMessage.getStatusEnum()).isEqualTo(StatusEnum.win);
        assertThat(gameMessage.getCalculateResult()).isEqualTo("4A0B");
    }


    @Test
    public void shoud_lose_the_game() {

        SessionStorage.put("lifeValue", "1");
        SessionStorage.put("status", "1");
        SessionStorage.put("answer", "1234");

        GameMessage gameMessage = new Route().route(CommandEnum.guess, "1357");

        assertThat(gameMessage.getLifeValue()).isEqualTo(0);
        assertThat(gameMessage.getStatusEnum()).isEqualTo(StatusEnum.failure);
        assertThat(gameMessage.getCalculateResult()).isEqualTo("1A1B");
        assertThat(gameMessage.getErrorMessage()).isEqualTo(GuessGame.ERROR_GAME_OVER);

    }


    @Test
    public void shoud_reduce_the_life_value() {

        SessionStorage.put("lifeValue", "4");
        SessionStorage.put("status", "1");
        SessionStorage.put("answer", "1234");

        GameMessage gameMessage = new Route().route(CommandEnum.guess, "1357");

        assertThat(gameMessage.getLifeValue()).isEqualTo(3);
    }


    @Test
    public void shoud_quit_the_game() {

        SessionStorage.put("lifeValue", "1");
        SessionStorage.put("status", "1");
        SessionStorage.put("answer", "1234");

        GameMessage gameMessage = new Route().route(CommandEnum.quit, null);
    }


}
