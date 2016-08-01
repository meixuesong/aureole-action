package core;

import java.util.HashSet;
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
    private static final Pattern NumberPatten = Pattern.compile("^[-\\+]?[\\d]*$");
    private final RandomNumberGenerator randomNumberGenerator;
    private final NumberMatcher numberMatcher;
    private final Player player;

    public String getAnswer() {
        return answer;
    }

    private String answer;

    public GuessGame(Player player,
                     NumberMatcher numberMatcher,
                     RandomNumberGenerator randomNumberGenerator) {
        this.player = player;
        this.randomNumberGenerator = randomNumberGenerator;
        this.numberMatcher = numberMatcher;
        this.answer = randomNumberGenerator.generateAnswer();
    }


    public Player getPlayer() {
        return player;
    }


    public String validate(String guessValue) {

        String result = null;

        if(guessValue == null || !NumberPatten.matcher(guessValue).matches() || guessValue.length() != 4) {
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

    public GameMessage start() {
        GameMessage gameMessage = new GameMessage();

        if(player.getStatus() != StatusEnum.playing) {
            answer = randomNumberGenerator.generateAnswer();
            player.setStatus(StatusEnum.playing);
            player.setLifeValue(6);
        } else {
            gameMessage.setErrorMessage(ERROR_THE_GAME_IS_STARTED);
        }

        gameMessage.setLifeValue(player.getLifeValue());
        gameMessage.setStatusEnum(player.getStatus());

        return gameMessage;
    }

    public GameMessage guess(String guessValue) {
        GameMessage gameMessage = new GameMessage();
        gameMessage.setErrorMessage(validate(guessValue));
        gameMessage.setStatusEnum(StatusEnum.playing);
        gameMessage.setLifeValue(player.getLifeValue());

        if(gameMessage.getErrorMessage() == null) {
            gameMessage.setCalculateResult(numberMatcher.match(answer, guessValue));
            gameMessage.setLifeValue(player.reduceLifeValue());
            if(gameMessage.getCalculateResult().equals("4A0B")) {
                gameMessage.setStatusEnum(StatusEnum.win);
                player.setStatus(StatusEnum.win);
                gameMessage.setErrorMessage(GAME_WIN);
            }
            else if(gameMessage.getLifeValue() == 0) {
                gameMessage.setStatusEnum(StatusEnum.failure);
                player.setStatus(StatusEnum.failure);
                gameMessage.setErrorMessage(ERROR_GAME_OVER);
            }

        }

        return gameMessage;

    }
}
