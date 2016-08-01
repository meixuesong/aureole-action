import static org.fest.assertions.api.Assertions.assertThat;

import core.RandomNumberGenerator;
import org.junit.Test;

import java.util.regex.Pattern;

/**
 * Created by lianghuang on 7/29/16.
 */
public class RandomNumberGeneratorTest {

    @Test
    public void should_generate_new_4_length_answer(){
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

        assertThat(randomNumberGenerator.generateAnswer().length()).isEqualTo(4);
    }

    @Test
    public void should_generate_new_number_answer(){

        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

        assertThat(isInteger(randomNumberGenerator.generateAnswer()));
    }

    @Test
    public void should_generate_4_different_number_answer() {

        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

        assertThat(isInteger(randomNumberGenerator.generateAnswer()));
        assertThat(isNotRepeat(randomNumberGenerator.generateAnswer()));
    }


    private boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    private boolean isNotRepeat(String answer) {
        int[] numberCounts = new int[10];
        for(int i=0; i < answer.length(); i ++) {
            numberCounts[answer.charAt(i) - 48] ++;
            if(numberCounts[answer.charAt(i) - 48] > 1) {
                return false;
            }
        }

        return true;
    }

}
