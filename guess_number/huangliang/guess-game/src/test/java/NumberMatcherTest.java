import static org.fest.assertions.api.Assertions.assertThat;

import core.NumberMatcher;
import org.junit.Test;

/**
 * Created by lianghuang on 7/29/16.
 */
public class NumberMatcherTest {

    @Test
    public void should_return_1A1B_when_match_1234_answer_is_1357() {
        String tips = new NumberMatcher().match("1357", "1234");
        assertThat(tips).isEqualTo("1A1B");
    }


    @Test
    public void should_return_4A0B_when_match_1357_answer_is_1357() {
        String tips = new NumberMatcher().match("1357", "1357");
        assertThat(tips).isEqualTo("4A0B");
    }

    @Test
    public void should_return_0A0B_when_match_2468_answer_is_1357() {
        String tips = new NumberMatcher().match("1357", "2468");
        assertThat(tips).isEqualTo("0A0B");
    }
}
