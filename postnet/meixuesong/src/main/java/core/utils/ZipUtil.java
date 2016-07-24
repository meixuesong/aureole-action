package core.utils;

import java.util.regex.Pattern;

/**
 * Created by Xuesong Mei on 7/24/16.
 */
public class ZipUtil {
    public boolean isValid(String value) {
        if (value == null) return false;

        return (isFiveNumbers(value) || isNineNumbers(value) || isNineNumbersWithHyphen(value));
    }

    private boolean isFiveNumbers(String value) {
        if (value.length() != 5) return false;

        String regex = "\\d{5}";
        return Pattern.matches(regex, value);
    }

    private boolean isNineNumbers(String value) {
        if (value.length() != 9) return false;

        String regex = "\\d{9}";
        return Pattern.matches(regex, value);
    }

    private boolean isNineNumbersWithHyphen(String value) {
        if (value.length() != 10) return false;

        String regex = "\\d{5}[-]\\d{4}";
        return Pattern.matches(regex, value);
    }
}
