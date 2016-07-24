package com.tw.barcode;

public class CommandResult {
    public final String reply;
    public final boolean end;

    public CommandResult(String reply, boolean end) {
        this.reply = reply;
        this.end = end;
    }
}
