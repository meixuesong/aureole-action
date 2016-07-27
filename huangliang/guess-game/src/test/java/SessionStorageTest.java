import static org.fest.assertions.api.Assertions.assertThat;

import core.SessionStorage;
import org.junit.Test;

/**
 * Created by lianghuang on 7/17/16.
 */
public class SessionStorageTest {

    @Test
    public void should_add_6_to_lifeValue() {
        SessionStorage.put("lifeValue", "6");

        assertThat(SessionStorage.get("lifeValue")).isEqualTo("6");
    }

    @Test
    public void should_add_1234_to_answer() {
        SessionStorage.put("answer", "1234");

        assertThat(SessionStorage.get("answer")).isEqualTo("1234");
    }

    @Test
    public void should_add_0_to_status() {
        SessionStorage.put("status", "0");

        assertThat(SessionStorage.get("status")).isEqualTo("0");
    }

    @Test
    public void should_add_1_to_status() {
        SessionStorage.put("status", "1");

        assertThat(SessionStorage.get("status")).isEqualTo("1");
    }

}
