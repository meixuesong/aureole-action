package com.tw.barcode.command;

import com.tw.barcode.Command;
import com.tw.barcode.CommandResult;

public class BarToZip implements Command {

    private TranslateBarToZip translateBarToZip;

    public BarToZip(TranslateBarToZip translateBarToZip) {
        this.translateBarToZip = translateBarToZip;
    }

    @Override
    public CommandResult apply(String zip) {
        return translateBarToZip.translate(zip);
    }
}
