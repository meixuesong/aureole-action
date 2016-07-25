package core;

import enumeration.CodeType;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zbwang on 16/7/17.
 */
public class CoreServices {

    private static final Map<String, String> zipToBar = new HashMap();
    private static final Map<String, String> barToZip = new HashMap();

    static {
        zipToBar.put("1", ":::||");
        zipToBar.put("2", "::|:|");
        zipToBar.put("3", "::||:");
        zipToBar.put("4", ":|::|");
        zipToBar.put("5", ":|:|:");
        zipToBar.put("6", ":||::");
        zipToBar.put("7", "|:::|");
        zipToBar.put("8", "|::|:");
        zipToBar.put("9", "|:|::");
        zipToBar.put("0", "||:::");

        barToZip.put(":::||", "1");
        barToZip.put("::|:|", "2");
        barToZip.put("::||:", "3");
        barToZip.put(":|::|", "4");
        barToZip.put(":|:|:", "5");
        barToZip.put(":||::", "6");
        barToZip.put("|:::|", "7");
        barToZip.put("|::|:", "8");
        barToZip.put("|:|::", "9");
        barToZip.put("||:::", "0");
    }


    public String translation(String src, CodeType codeType) {
        if (codeType.equals(CodeType.ZIP)) {
            if (!validateZipCode(src)){
                return "Please give right input";
            }
            src = src.replace("-", "");
            src = src + calcuteCheckDigit(src);
            StringBuilder result = new StringBuilder();
            for (Character c : src.toCharArray()) {
                String barStr = mappingWord(c.toString(), CodeType.ZIP);
                if (barStr == null) {
                    return "Please give right input";
                }
                result.append(barStr);
            }
            return "|" + result.toString() + "|";
        }

        if (codeType.equals(CodeType.BAR)) {
            //去除前后竖线，1
            try {
                src = src.substring(1, src.length() - 1);
                // 每五个拆分，翻译单个后拼接，
                StringBuilder sbResult = new StringBuilder();
                for (int i = 0; i < src.length(); i += 5) {
                    sbResult.append(mappingWord(src.substring(i, i + 5), codeType));
                }
                // 校验cd
                String result = sbResult.substring(0, sbResult.length() - 1);
                String cdValue = sbResult.substring(sbResult.length() - 1, sbResult.length());
                if (!StringUtils.equals(calcuteCheckDigit(result), cdValue)) {
                    return "Please give right input";
                }
                return result;
            } catch (StringIndexOutOfBoundsException e) {
                return "Please give right input";
            }
        }

        return "Please give right type";
    }

    private boolean validateZipCode(String src) {
        if (src.contains("-")) {
            if (src.length() != 10 || src.indexOf("-") != 5) {
                return false;
            }
        } else {
            if (src.length() != 5 && src.length() != 9) {
                return false;
            }
        }
        return true;
    }

    private String calcuteCheckDigit(String source) {
        int sum = 0;
        for (Character c : source.toCharArray()) {
            try {
                sum += Integer.valueOf(c.toString());
            } catch (NumberFormatException e) {
                return "Please give right input";
            }
        }
        return String.valueOf((10 - sum % 10) % 10);
    }

    private String mappingWord(String word, CodeType codeType) {
        if (codeType.equals(CodeType.ZIP)) {
            return zipToBar.get(word);
        }
        if (codeType.equals(CodeType.BAR)) {
            return barToZip.get(word);
        }
        return "Please give right type";
    }

}
