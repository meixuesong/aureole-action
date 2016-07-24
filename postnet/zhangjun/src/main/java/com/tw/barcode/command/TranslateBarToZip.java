package com.tw.barcode.command;

import com.tw.barcode.CommandResult;

import java.util.HashMap;
import java.util.Map;

public class TranslateBarToZip {
    private Map<String, Character> barToZips = new HashMap();
    {
        barToZips.put(":::||", '1');
        barToZips.put("::|:|", '2');
        barToZips.put("::||:", '3');
        barToZips.put(":|::|", '4');
        barToZips.put(":|:|:", '5');
        barToZips.put(":||::", '6');
        barToZips.put("|:::|", '7');
        barToZips.put("|::|:", '8');
        barToZips.put("|:|::", '9');
        barToZips.put("||:::", '0');
    }

    public CommandResult translate(String bar) {
        String zip = doTranslate(bar);
        String noCD = zip.substring(0, zip.length() - 1);
        String cd = zip.substring(zip.length() - 1);

        if(!checkCD(noCD, Integer.parseInt(cd))) {
            return new CommandResult("error cd", false);
        }
        zip = removeCD(zip);
        return new CommandResult(addHyphon(zip), true);
    }

    private String removeCD(String zip) {
        return zip.substring(0, zip.length() - 1);
    }

    private String doTranslate(String barStr) {
        barStr = barStr.substring(0, barStr.length() - 1).substring(1);
        StringBuilder buf = new StringBuilder();
        for (String bar : splitBar(barStr.toCharArray())) {
            buf.append(barToZips.get(bar));
        }
        return buf.toString();
    }

    private String[] splitBar(char[] chars) {
        String[] bars = new String[chars.length / 5];
        for(int i=0; i<bars.length; i++) {
            bars[i] = new String(chars, i*5, 5);
        }
        return bars;
    }

    private boolean checkCD(String zipStr, int cd) {
        int sum = 0;
        for (char zip : zipStr.toCharArray()) {
            sum += Character.getNumericValue(zip);
        }
        return sum % 10 == cd;
    }

    private String addHyphon(String zip) {
        return zip.length() == 9 ? zip.substring(0, 5) + "-" + zip.substring(5) : zip;
    }

    String validate(String userInput) {
        if(userInput == null) {
            return "null";
        }

        int length = userInput.length();
        if(length != 32 && length != 52 && length != 57) {
            return "length error";
        }

        if (!userInput.matches("[:|]{32,}")) {
            return "character error";
        }

        return null;
    }

}
