package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by lianghuang on 7/17/16.
 */
public class GuessGame {

    private static final String ERROR_MESSAGE = "The guess number must be 4 different digits.";

    private static final Pattern NumberPatten = Pattern.compile("^[-\\+]?[\\d]*$");
    private final PrintStream out;
    private final BufferedReader reader;
    private final NumberMatcher numberMatcher;
    private final RandomNumberGenerator randomNumberGenerator;

    public GuessGame(PrintStream out, BufferedReader reader, NumberMatcher numberMatcher, RandomNumberGenerator
        randomNumberGenerator) {

        this.out = out;
        this.reader = reader;
        this.numberMatcher = numberMatcher;
        this.randomNumberGenerator = randomNumberGenerator;
    }


    public void start() throws IOException {

        String answer = randomNumberGenerator.generateAnswer();
        int chance = 6;
        while (chance > 0) {
            out.printf("Please input your number(" + chance + "): ");
            String input = reader.readLine();
            Optional<String> message = validate(input);
            if(message.isPresent()) {
                out.println(message.get());
                continue;
            }

            String tips = numberMatcher.match(answer, input);

            if(tips.equals("4A0B")) {
                out.println("Congratulate!!");
                return;
            }

            out.println(tips);
            chance --;
        }

        out.println("Game Over");

    }

    private Optional<String> validate(String guessValue) {

        Optional<String> result = Optional.empty();

        if (guessValue == null || !NumberPatten.matcher(guessValue).matches() || guessValue.length() != 4) {
            result = Optional.of(ERROR_MESSAGE);
        } else {
            Set<Character> characterSet = new HashSet<Character>();

            for (int i = 0; i < guessValue.length(); i++) {
                characterSet.add(guessValue.charAt(i));
            }

            if (characterSet.size() != 4) {
                result = Optional.of(ERROR_MESSAGE);
            }
        }

        return result;
    }
}
