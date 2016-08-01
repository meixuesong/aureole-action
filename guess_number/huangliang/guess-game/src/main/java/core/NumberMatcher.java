package core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lianghuang on 7/29/16.
 */
public class NumberMatcher {

    public String match(String answer, String guessValue) {
        Map<Character,Boolean> answerMap = new HashMap<Character, Boolean>();
        for (Character c: answer.toCharArray()) {
            answerMap.put(c, true);
        }

        Integer rightNumberAndPosition = 0;
        Integer rightNumberAndWrongPosition = 0;
        for(int i =0; i < 4; i ++) {
            if(answerMap.containsKey(guessValue.charAt(i))) {
                if(answer.charAt(i) == guessValue.charAt(i)) {
                    rightNumberAndPosition ++;
                } else {
                    rightNumberAndWrongPosition ++;
                }
            }
        }

        return String.valueOf(rightNumberAndPosition) + "A"
            + String.valueOf(rightNumberAndWrongPosition) + "B";
    }
}
