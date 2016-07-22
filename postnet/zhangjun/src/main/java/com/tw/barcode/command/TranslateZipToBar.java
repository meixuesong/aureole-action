package com.tw.barcode.command;

import com.tw.barcode.Command;
import com.tw.barcode.Router;

import java.util.HashMap;
import java.util.Map;

public class TranslateZipToBar {

    private Map<Character, String> zipToBars = new HashMap();
    {
        zipToBars.put('1', ":::||");
        zipToBars.put('2', "::|:|");
        zipToBars.put('3', "::||:");
        zipToBars.put('4', ":|::|");
        zipToBars.put('5', ":|:|:");
        zipToBars.put('6', ":||::");
        zipToBars.put('7', "|:::|");
        zipToBars.put('8', "|::|:");
        zipToBars.put('9', "|:|::");
        zipToBars.put('0', "||:::");
    }

    public String translate(String zip) {
        zip = removeHyphon(zip);
        int cd = checkCD(zip);
        zip = addCD(zip, cd);
        return doTranslate(zip);
    }

    private String doTranslate(String zipStr) {
        StringBuilder buf = new StringBuilder();
        for (char zip : zipStr.toCharArray()) {
            buf.append(zipToBars.get(zip));
        }
        return "|" + buf.toString() + "|";
    }
    private String addCD(String zip, int cd) {
        return zip + cd;
    }

    private int checkCD(String zipStr) {
        int sum = 0;
        for (char zip : zipStr.toCharArray()) {
            sum += Character.getNumericValue(zip);
        }
        return sum % 10;
    }

    private String removeHyphon(String zip) {
        return zip.replace("-", "");
    }

    public String validate(String userInput) {
        if(userInput == null) {
            return "null";
        }

        if (!userInput.matches("\\d+-?\\d+")) {
            return "character error";
        }

        int length = userInput.length();
        if(length != 5 && length != 10) {
            return "length error";
        }

        return null;
    }

}
