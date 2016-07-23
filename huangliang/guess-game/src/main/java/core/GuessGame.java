package core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by lianghuang on 7/17/16.
 */
public class GuessGame {

    public static final String ERROR_MESSAGE = "The guess number must be 4 digits.";
    public static final String ERROR_GAME_OVER = "Game over!";
    public static final String GAME_WIN = "You are Winner!";
    private static final String ERROR_THE_GAME_IS_STARTED = "The game is ongoing, you can't start a new game";


    public void initialize() {

        SessionStorage.put("lifeValue", "6");
        SessionStorage.put("status", "1");
        SessionStorage.put("answer", null);
    }

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

        SessionStorage.put("answer", result);

        return result;
    }

    public String match(String guessValue) {
        String answer = SessionStorage.get("answer");
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
                }
                else {
                    rightNumberAndWrongPosition ++;
                }
            }
        }

        return String.valueOf(rightNumberAndPosition) + "A"
            + String.valueOf(rightNumberAndWrongPosition) + "B";
    }

    public String validate(String guessValue) {

        String result = null;

        if(guessValue == null || !isInteger(guessValue) || guessValue.length() != 4) {
            result = ERROR_MESSAGE;
        } else {
            Set<Character> characterSet = new HashSet<Character>();

            for(int i=0; i < guessValue.length(); i ++) {
                characterSet.add(guessValue.charAt(i));
            }

            if(characterSet.size() != 4) {
                result = ERROR_MESSAGE;
            }
        }


        return result;
    }

    private boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public Integer reduceLifeValue() {
        Integer lifeValue = Integer.valueOf(SessionStorage.get("lifeValue"));
        lifeValue = lifeValue > 0 ? --lifeValue : 0;
        SessionStorage.put("lifeValue", String.valueOf(lifeValue));

        return lifeValue;
    }

    public GameMessage startNew() {
        GameMessage gameMessage = new GameMessage();
        Integer status = Integer.valueOf(SessionStorage.get("status"));
        gameMessage.setStatusEnum(StatusEnum.valueOf(status));

        if(status != 1) {
            initialize();
            generateAnswer();
            gameMessage.setStatusEnum(StatusEnum.playing);
            gameMessage.setLifeValue(Integer.valueOf(SessionStorage.get("lifeValue")));
        } else {
            gameMessage.setLifeValue(Integer.valueOf(SessionStorage.get("lifeValue")));
            gameMessage.setErrorMessage(ERROR_THE_GAME_IS_STARTED);
        }

        return gameMessage;
    }

    public GameMessage calculate(String guessValue) {
        GameMessage gameMessage = new GameMessage();
        gameMessage.setErrorMessage(validate(guessValue));
        gameMessage.setStatusEnum(StatusEnum.playing);
        gameMessage.setLifeValue(Integer.valueOf(SessionStorage.get("lifeValue")));

        if(gameMessage.getErrorMessage() == null) {
            gameMessage.setCalculateResult(match(guessValue));
            gameMessage.setLifeValue(reduceLifeValue());
            if(gameMessage.getCalculateResult().equals("4A0B")) {
                gameMessage.setStatusEnum(StatusEnum.win);
                SessionStorage.put("status", StatusEnum.win.getValue().toString());
                gameMessage.setErrorMessage(GAME_WIN);
            }
            else if(gameMessage.getLifeValue() == 0) {
                gameMessage.setStatusEnum(StatusEnum.failure);
                SessionStorage.put("status", StatusEnum.failure.getValue().toString());
                gameMessage.setErrorMessage(ERROR_GAME_OVER);
            }

        }

        return gameMessage;

    }
}
