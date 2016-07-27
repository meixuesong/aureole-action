import static org.fest.assertions.api.Assertions.assertThat;

import core.GameMessage;
import core.RandomNumberGenerator;
import core.GuessGame;
import core.StatusEnum;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by lianghuang on 7/17/16.
 */
public class GuessGameTest {

    @Test
    public void should_be_initialized(){
        GuessGame guessGame = new GuessGame(new RandomNumberGenerator());
        guessGame.initialize();

        assertThat(guessGame.getLifeValue()).isEqualTo(6);
        assertThat(guessGame.getStatus()).isEqualTo(StatusEnum.playing);
    }


    @Test
    public void should_generate_new_4_length_answer(){
        GuessGame guessGame = new GuessGame(new RandomNumberGenerator());
        guessGame.initialize();

        assertThat(guessGame.getAnswer().length()).isEqualTo(4);
    }


    @Test
    public void should_generate_new_number_answer(){

        GuessGame guessGame = new GuessGame(new RandomNumberGenerator());
        guessGame.initialize();

        assertThat(isInteger(guessGame.getAnswer()));
    }

    @Test
    public void should_generate_4_different_number_answer(){

        GuessGame guessGame = new GuessGame(new RandomNumberGenerator());
        guessGame.initialize();

        assertThat(isNotRepeat(guessGame.getAnswer()));
        assertThat(isInteger(guessGame.getAnswer()));
    }

    @Test
    public void should_return_1A1B_when_match_1234_answer_is_1357() {

        RandomNumberGenerator randomNumberGenerator = mock(RandomNumberGenerator.class);
        when(randomNumberGenerator.generateAnswer()).thenReturn("1357");
        GuessGame guessGame = new GuessGame(randomNumberGenerator);
        guessGame.initialize();

        String tips = guessGame.match("1234");
        assertThat(tips).isEqualTo("1A1B");
    }


    @Test
    public void should_return_4A0B_when_match_1357_answer_is_1357() {
        RandomNumberGenerator randomNumberGenerator = mock(RandomNumberGenerator.class);
        when(randomNumberGenerator.generateAnswer()).thenReturn("1357");
        GuessGame guessGame = new GuessGame(randomNumberGenerator);
        guessGame.initialize();

        String tips = guessGame.match("1357");
        assertThat(tips).isEqualTo("4A0B");
    }

    @Test
    public void should_return_0A0B_when_match_2468_answer_is_1357() {
        RandomNumberGenerator randomNumberGenerator = mock(RandomNumberGenerator.class);
        when(randomNumberGenerator.generateAnswer()).thenReturn("1357");
        GuessGame guessGame = new GuessGame(randomNumberGenerator);
        guessGame.initialize();

        String tips = guessGame.match("2468");
        assertThat(tips).isEqualTo("0A0B");
    }


    @Test
    public void should_return_null_when_validate_1234() {
        GuessGame guessGame = new GuessGame(new RandomNumberGenerator());

        String errorMessage = guessGame.validate("1234");
        assertThat(errorMessage == null);
    }


    @Test
    public void should_return_error_message_when_validate_abcd() {
        GuessGame guessGame = new GuessGame(new RandomNumberGenerator());
        String errorMessage = guessGame.validate("abcd");
        assertThat(errorMessage).isEqualTo(GuessGame.ERROR_MESSAGE);
    }

    @Test
    public void should_return_error_message_when_validate_abc() {
        GuessGame guessGame = new GuessGame(new RandomNumberGenerator());
        String errorMessage = guessGame.validate("abc");
        assertThat(errorMessage).isEqualTo(GuessGame.ERROR_MESSAGE);
    }


    @Test
    public void should_return_error_message_when_validate_1() {
        GuessGame guessGame = new GuessGame(new RandomNumberGenerator());
        String errorMessage = guessGame.validate("1");
        assertThat(errorMessage).isEqualTo(GuessGame.ERROR_MESSAGE);
    }


    @Test
    public void should_return_error_message_when_validate_null() {
        GuessGame guessGame = new GuessGame(new RandomNumberGenerator());
        String errorMessage = guessGame.validate(null);
        assertThat(errorMessage).isEqualTo(GuessGame.ERROR_MESSAGE);
    }

    @Test
    public void should_return_error_message_when_validate_1123() {
        GuessGame guessGame = new GuessGame(new RandomNumberGenerator());
        String errorMessage = guessGame.validate("1123");
        assertThat(errorMessage).isEqualTo(GuessGame.ERROR_MESSAGE);
    }

    @Test
    public void should_reduce_the_life_value_when_the_value_is_6() {
        GuessGame guessGame = new GuessGame(new RandomNumberGenerator());
        guessGame.initialize();
        Integer lifeValue = guessGame.reduceLifeValue();
        assertThat(lifeValue).isEqualTo(5);
    }

    @Test
    public void should_reduce_the_life_value_when_the_value_is_0() throws NoSuchFieldException, IllegalAccessException {

        GuessGame guessGame = new GuessGame(new RandomNumberGenerator());

        setPrivateFieldValue(guessGame, "lifeValue", 0);

        Integer lifeValue = guessGame.reduceLifeValue();
        assertThat(lifeValue).isEqualTo(0);
    }

    @Test
    public void should_start_a_new_game() {

        GuessGame guessGame = new GuessGame(new RandomNumberGenerator());
        guessGame.startNew();

        assertThat(guessGame.getLifeValue()).isEqualTo(6);
        assertThat(guessGame.getStatus()).isEqualTo(StatusEnum.playing);
        assertThat(guessGame.getAnswer().length()).isEqualTo(4);
        assertThat(isInteger(guessGame.getAnswer()));
        assertThat(isNotRepeat(guessGame.getAnswer()));
    }

    @Test
    public void should_keep_a_ongoing_game() {
        GuessGame guessGame = new GuessGame(new RandomNumberGenerator());
        guessGame.initialize();
        setPrivateFieldValue(guessGame, "lifeValue", 4);
        setPrivateFieldValue(guessGame, "status", StatusEnum.playing);
        setPrivateFieldValue(guessGame, "answer", "1234");

        guessGame.startNew();

        assertThat(guessGame.getLifeValue()).isEqualTo(4);
        assertThat(guessGame.getStatus()).isEqualTo(StatusEnum.playing);
        assertThat(guessGame.getAnswer()).isEqualTo("1234");
    }

    @Test
    public void should_return_error_info() {
        GuessGame guessGame = new GuessGame(new RandomNumberGenerator());
        guessGame.startNew();

        GameMessage gameMessage = guessGame.calculate("1A23");

        assertThat(gameMessage.getErrorMessage()).isEqualTo(GuessGame.ERROR_MESSAGE);
    }

    @Test
    public void should_return_normal_status() {

        GuessGame guessGame = new GuessGame(new RandomNumberGenerator());
        guessGame.initialize();
        setPrivateFieldValue(guessGame, "lifeValue", 6);
        setPrivateFieldValue(guessGame, "status", StatusEnum.playing);
        setPrivateFieldValue(guessGame, "answer", "1234");

        GameMessage gameMessage = guessGame.calculate("1357");


        assertThat(gameMessage.getLifeValue()).isEqualTo(5);
        assertThat(gameMessage.getStatusEnum()).isEqualTo(StatusEnum.playing);
        assertThat(gameMessage.getCalculateResult()).isEqualTo("1A1B");

    }

    @Test
    public void should_return_failure_status() {
        GuessGame guessGame = new GuessGame(new RandomNumberGenerator());
        guessGame.initialize();
        setPrivateFieldValue(guessGame, "lifeValue", 1);
        setPrivateFieldValue(guessGame, "status", StatusEnum.playing);
        setPrivateFieldValue(guessGame, "answer", "1234");

        GameMessage gameMessage = guessGame.calculate("1357");

        assertThat(gameMessage.getLifeValue()).isEqualTo(0);
        assertThat(gameMessage.getStatusEnum()).isEqualTo(StatusEnum.failure);
        assertThat(gameMessage.getCalculateResult()).isEqualTo("1A1B");
        assertThat(gameMessage.getErrorMessage()).isEqualTo(GuessGame.ERROR_GAME_OVER);

    }

    @Test
    public void should_return_success_status() {
        GuessGame guessGame = new GuessGame(new RandomNumberGenerator());
        guessGame.initialize();
        setPrivateFieldValue(guessGame, "lifeValue", 1);
        setPrivateFieldValue(guessGame, "status", StatusEnum.playing);
        setPrivateFieldValue(guessGame, "answer", "1234");

        GameMessage gameMessage = guessGame.calculate("1234");

        assertThat(gameMessage.getLifeValue()).isEqualTo(0);
        assertThat(gameMessage.getStatusEnum()).isEqualTo(StatusEnum.win);
        assertThat(gameMessage.getCalculateResult()).isEqualTo("4A0B");
        assertThat(gameMessage.getErrorMessage()).isEqualTo(GuessGame.GAME_WIN);

    }


    private void setPrivateFieldValue(GuessGame guessGame, String fieldName, Object value) {
        Field privateField = null;
        try {
            privateField = GuessGame.class.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        privateField.setAccessible(true);
        try {
            privateField.set(guessGame, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
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
