package com.tw.barcode;

import com.tw.barcode.command.*;

import java.util.HashMap;
import java.util.Map;

public class Router {
    public Command command;

    public String request(String userInput) {
        if (command == null) {
            command = new AppUseage(this);
        }

        return command.apply(userInput);
    }

}
