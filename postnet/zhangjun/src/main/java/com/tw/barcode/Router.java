package com.tw.barcode;

import com.tw.barcode.command.*;

import java.util.HashMap;
import java.util.Map;

public class Router {

    private final Factory factory;
    private Map<String, Command> mapping;

    Command command;

    public Router(Factory factory) {
        this.factory = factory;

        mapping = new HashMap<String, Command>();
        mapping.put("1", factory.newZipToBar());
        mapping.put("2", factory.newBarToZip());
        mapping.put("3", factory.newAppExit());
    }

    public String request(String userInput) {
        if (command == null) {
            command = mapping.get(userInput);
        }

        if (command == null) {
            command = factory.newAppUseage();
        }

        CommandResult result = command.apply(userInput);
        if(result.end) {
            command = null;
        }

        return result.reply;
    }

}
