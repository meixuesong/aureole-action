package com.tw.barcode.command;

import com.tw.barcode.Command;
import com.tw.barcode.CommandResult;

public class ZipToBar implements Command {
    @Override
    public CommandResult apply(String zip) {
        return new TranslateZipToBar().translate(zip);
    }
}
