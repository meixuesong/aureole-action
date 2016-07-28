package core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by lianghuang on 7/17/16.
 */
public class GuessGame {

    public static final String ERROR_MESSAGE = "The guess number must be 4 different digits.";
    public static final String ERROR_GAME_OVER = "Game over!";
    public static final String GAME_WIN = "You are Winner!";
    private static final String ERROR_THE_GAME_IS_STARTED = "The game is ongoing, you can't start a new game";
    private final RandomNumberGenerator randomNumberGenerator;
    private final GuessGameContext guessGameContext;

    public GuessGame(GuessGameContext guessGameContext, RandomNumberGenerator randomNumberGenerator) {
        this.guessGameContext = guessGameContext;
        this.randomNumberGenerator = randomNumberGenerator;
    }


    public GuessGameContext getGuessGameContext() {
        return guessGameContext;
    }

    public String match(String guessValue) {
        String answer = guessGameContext.getAnswer();
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
        Integer lifeValue = guessGameContext.getLifeValue() > 0 ? guessGameContext.getLifeValue() - 1 : 0;
        guessGameContext.setLifeValue(lifeValue);

        return lifeValue;
    }

    public GameMessage startNew() {
        StatusEnum status = guessGameContext.getStatus();
        Integer lifeValue = guessGameContext.getLifeValue() ;

        GameMessage gameMessage = new GameMessage();
        gameMessage.setStatusEnum(status);

        if(status != StatusEnum.playing) {
            guessGameContext.initialize(randomNumberGenerator.generateAnswer());
            gameMessage.setStatusEnum(StatusEnum.playing);
            gameMessage.setLifeValue(lifeValue);
        } else {
            gameMessage.setLifeValue(lifeValue);
            gameMessage.setErrorMessage(ERROR_THE_GAME_IS_STARTED);
        }

        return gameMessage;
    }

    public GameMessage calculate(String guessValue) {
        GameMessage gameMessage = new GameMessage();
        gameMessage.setErrorMessage(validate(guessValue));
        gameMessage.setStatusEnum(StatusEnum.playing);
        gameMessage.setLifeValue(guessGameContext.getLifeValue());

        if(gameMessage.getErrorMessage() == null) {
            gameMessage.setCalculateResult(match(guessValue));
            gameMessage.setLifeValue(reduceLifeValue());
            if(gameMessage.getCalculateResult().equals("4A0B")) {
                gameMessage.setStatusEnum(StatusEnum.win);
                guessGameContext.setStatus(StatusEnum.win);
                gameMessage.setErrorMessage(GAME_WIN);
            }
            else if(gameMessage.getLifeValue() == 0) {
                gameMessage.setStatusEnum(StatusEnum.failure);
                guessGameContext.setStatus(StatusEnum.failure);
                gameMessage.setErrorMessage(ERROR_GAME_OVER);
            }

        }

        return gameMessage;

    }
}
