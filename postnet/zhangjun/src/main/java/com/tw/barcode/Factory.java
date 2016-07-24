package com.tw.barcode;

import com.tw.barcode.command.*;

public class Factory {
    public Command newZipToBar() {
        return new ZipToBar(new TranslateZipToBar());
    }

    public Command newBarToZip() {
        return new BarToZip(new TranslateBarToZip());
    }

    public Command newAppExit() {
        return new AppExit();
    }

    public Command newAppUseage() {
        return new AppUseage();
    }
}
