package com.tw.barcode;

import com.tw.barcode.command.*;

import java.util.HashMap;
import java.util.Map;

public class Router {
    Command command;
    private Map<String, Command> mapping;
    {
        mapping = new HashMap<String, Command>();
        mapping.put("1", new ZipToBar());
        mapping.put("2", new BarToZip());
        mapping.put("3", new AppExit());
    }

    public String request(String userInput) {
        if (command == null) {
            command = mapping.get(userInput);
        }

        if (command == null) {
            command = new AppUseage();
        }

        CommandResult result = command.apply(userInput);
        if(result.end) {
            command = null;
        }

        return result.reply;
    }

}
