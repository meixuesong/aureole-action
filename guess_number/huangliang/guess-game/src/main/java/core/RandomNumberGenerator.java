package core;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by lianghuang on 7/27/16.
 */
public class RandomNumberGenerator {

    public String generateAnswer() {
        Map<String,Boolean> map = new HashMap<String, Boolean>();
        String result = "";

        while(true) {
            String current = String.valueOf(Math.abs(new Random().nextInt()) % 10);
            if(!map.containsKey(current)){
                map.put(current,true);
                result += current;
            }

            if(result.length() >= 4) {
                break;
            }
        }

        return result;
    }
}
