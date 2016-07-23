import static org.fest.assertions.api.Assertions.assertThat;

import core.GameMessage;
import core.GuessGame;
import core.SessionStorage;
import core.StatusEnum;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by lianghuang on 7/17/16.
 */
public class GuessGameTest {

    @Test
    public void should_be_initialized(){
        SessionStorage.put("lifeValue", "3");
        SessionStorage.put("status", "2");
        SessionStorage.put("answer", "1234");

        new GuessGame().initialize();

        assertThat(SessionStorage.get("lifeValue")).isEqualTo("6");
        assertThat(SessionStorage.get("status")).isEqualTo("1");
        assertThat(SessionStorage.get("answer")).isEqualTo(null);
    }


    @Test
    public void should_generate_new_4_length_answer(){

        String answer = new GuessGame().generateAnswer();

        assertThat(answer.length()).isEqualTo(4);
    }


    @Test
    public void should_generate_new_number_answer(){

        String answer = new GuessGame().generateAnswer();

        assertThat(isInteger(answer));
    }

    @Test
    public void should_generate_4_different_number_answer(){

        String answer = new GuessGame().generateAnswer();

        assertThat(isNotRepeat(answer));
        assertThat(isInteger(answer));
    }

    @Test
    public void should_return_1A1B_when_match_1234_answer_is_1357() {
        SessionStorage.put("answer", "1357");
        String tips = new GuessGame().match("1234");
        assertThat(tips).isEqualTo("1A1B");
    }


    @Test
    public void should_return_4A0B_when_match_1357_answer_is_1357() {
        SessionStorage.put("answer", "1357");
        String tips = new GuessGame().match("1357");
        assertThat(tips).isEqualTo("4A0B");
    }

    @Test
    public void should_return_0A0B_when_match_2468_answer_is_1357() {
        SessionStorage.put("answer", "1357");
        String tips = new GuessGame().match("2468");
        assertThat(tips).isEqualTo("0A0B");
    }


    @Test
    public void should_return_null_when_validate_1234() {
        String errorMessage = new GuessGame().validate("1234");
        assertThat(errorMessage == null);
    }


    @Test
    public void should_return_error_message_when_validate_abcd() {
        String errorMessage = new GuessGame().validate("abcd");
        assertThat(errorMessage).isEqualTo(GuessGame.ERROR_MESSAGE);
    }

    @Test
    public void should_return_error_message_when_validate_abc() {
        String errorMessage = new GuessGame().validate("abc");
        assertThat(errorMessage).isEqualTo(GuessGame.ERROR_MESSAGE);
    }


    @Test
    public void should_return_error_message_when_validate_1() {
        String errorMessage = new GuessGame().validate("1");
        assertThat(errorMessage).isEqualTo(GuessGame.ERROR_MESSAGE);
    }


    @Test
    public void should_return_error_message_when_validate_null() {
        String errorMessage = new GuessGame().validate(null);
        assertThat(errorMessage).isEqualTo(GuessGame.ERROR_MESSAGE);
    }

    @Test
    public void should_return_error_message_when_validate_1123() {
        String errorMessage = new GuessGame().validate("1123");
        assertThat(errorMessage).isEqualTo(GuessGame.ERROR_MESSAGE);
    }

    @Test
    public void should_reduce_the_life_value_when_the_value_is_6() {
        SessionStorage.put("lifeValue","6");
        Integer lifeValue = new GuessGame().reduceLifeValue();
        assertThat(lifeValue).isEqualTo(5);
    }

    @Test
    public void should_reduce_the_life_value_when_the_value_is_0() {
        SessionStorage.put("lifeValue","0");
        Integer lifeValue = new GuessGame().reduceLifeValue();
        assertThat(lifeValue).isEqualTo(0);
    }

    @Test
    public void should_start_a_new_game() {
        SessionStorage.put("status", "0");

        new GuessGame().startNew();
        Integer lifeValue = Integer.valueOf(SessionStorage.get("lifeValue"));
        String status = SessionStorage.get("status");
        String answer = SessionStorage.get("answer");
        assertThat(lifeValue).isEqualTo(6);
        assertThat(status).isEqualTo("1");
        assertThat(answer.length()).isEqualTo(4);
        assertThat(isInteger(answer));
        assertThat(isNotRepeat(answer));


    }

    @Test
    public void should_keep_a_ongoing_game() {

        SessionStorage.put("lifeValue", "4");
        SessionStorage.put("status", "1");
        SessionStorage.put("answer", "1234");

        new GuessGame().startNew();

        Integer lifeValue = Integer.valueOf(SessionStorage.get("lifeValue"));
        String status = SessionStorage.get("status");
        String answer = SessionStorage.get("answer");
        assertThat(lifeValue).isEqualTo(4);
        assertThat(status).isEqualTo("1");
        assertThat(answer.length()).isEqualTo(4);
        assertThat(isInteger(answer));
        assertThat(isNotRepeat(answer));
        assertThat(answer).isEqualTo("1234");
    }

    @Test
    public void should_return_error_info() {
        SessionStorage.put("status", "0");
        new GuessGame().startNew();
        GameMessage gameMessage = new GuessGame().calculate("1A23");

        assertThat(gameMessage.getErrorMessage()).isEqualTo(GuessGame.ERROR_MESSAGE);

    }

    @Test
    public void should_return_normal_status() {
        SessionStorage.put("lifeValue", "6");
        SessionStorage.put("status", "1");
        SessionStorage.put("answer", "1234");

        GameMessage gameMessage = new GuessGame().calculate("1357");

        assertThat(gameMessage.getLifeValue()).isEqualTo(5);
        assertThat(gameMessage.getStatusEnum().getValue()).isEqualTo(1);
        assertThat(gameMessage.getCalculateResult()).isEqualTo("1A1B");

    }

    @Test
    public void should_return_failure_status() {
        SessionStorage.put("lifeValue", "1");
        SessionStorage.put("status", "1");
        SessionStorage.put("answer", "1234");

        GameMessage gameMessage = new GuessGame().calculate("1357");

        assertThat(gameMessage.getLifeValue()).isEqualTo(0);
        assertThat(gameMessage.getStatusEnum()).isEqualTo(StatusEnum.failure);
        assertThat(gameMessage.getCalculateResult()).isEqualTo("1A1B");
        assertThat(gameMessage.getErrorMessage()).isEqualTo(GuessGame.ERROR_GAME_OVER);

    }

    @Test
    public void should_return_success_status() {
        SessionStorage.put("lifeValue", "1");
        SessionStorage.put("status", "1");
        SessionStorage.put("answer", "1234");

        GameMessage gameMessage = new GuessGame().calculate("1234");

        assertThat(gameMessage.getLifeValue()).isEqualTo(0);
        assertThat(gameMessage.getStatusEnum()).isEqualTo(StatusEnum.win);
        assertThat(gameMessage.getCalculateResult()).isEqualTo("4A0B");
        assertThat(gameMessage.getErrorMessage()).isEqualTo(GuessGame.GAME_WIN);

    }



    private boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    private boolean isNotRepeat(String answer) {
        Set<Character> characterSet = new HashSet<Character>();

        for(int i=0; i < answer.length(); i ++) {
            characterSet.add(answer.charAt(i));
        }

        return characterSet.size() == answer.length();
    }

}
