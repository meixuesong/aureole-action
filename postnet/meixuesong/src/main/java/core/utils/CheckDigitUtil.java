package core.utils;

/**
 * Created by Xuesong Mei on 7/24/16.
 */
public class CheckDigitUtil {
    public int calc(String value) {
        int sum = 0;

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c >= '0' && c <= '9') {
                sum = sum + char2int(c);
            }
        }

        if (sum % 10 > 0) {
            return 10 - sum % 10;
        } else {
            return 0;
        }
    }

    private int char2int(char c) {
        return Integer.valueOf(String.valueOf(c));
    }
}
