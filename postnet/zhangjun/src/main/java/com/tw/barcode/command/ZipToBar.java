package com.tw.barcode.command;

import com.tw.barcode.Command;
import com.tw.barcode.CommandResult;

public class ZipToBar implements Command {

    private TranslateZipToBar translateZipToBar;

    public ZipToBar(TranslateZipToBar translateZipToBar) {
        this.translateZipToBar = translateZipToBar;
    }

    @Override
    public CommandResult apply(String zip) {
        return translateZipToBar.translate(zip);
    }
}
