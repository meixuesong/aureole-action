import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import core.Player;
import org.junit.Test;

/**
 * Created by lianghuang on 7/29/16.
 */
public class PlayerTest {

    @Test
    public void should_reduce_the_life_value_when_the_value_is_6() {
        Player player = new Player();
        player.initialize(null);
        assertThat(player.reduceLifeValue()).isEqualTo(5);
    }

    @Test
    public void should_reduce_the_life_value_when_the_value_is_0() throws NoSuchFieldException, IllegalAccessException {

        Player player = new Player();
        player.setLifeValue(0);
        assertThat(player.reduceLifeValue()).isEqualTo(0);
    }
}
