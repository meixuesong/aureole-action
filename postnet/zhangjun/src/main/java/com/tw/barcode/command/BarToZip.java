package com.tw.barcode.command;

import com.tw.barcode.Command;
import com.tw.barcode.CommandResult;

public class BarToZip implements Command {

    @Override
    public CommandResult apply(String zip) {
        return new TranslateBarToZip().translate(zip);
    }
}
