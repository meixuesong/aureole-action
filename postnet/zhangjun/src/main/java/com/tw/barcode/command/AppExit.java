package com.tw.barcode.command;

import com.tw.barcode.Command;
import com.tw.barcode.CommandResult;

public class AppExit implements Command {
    @Override
    public CommandResult apply(String userInput) {
        System.exit(0);

        throw new Error("unreached code");
    }
}
