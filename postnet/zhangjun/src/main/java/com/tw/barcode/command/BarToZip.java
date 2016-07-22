package com.tw.barcode.command;

import com.tw.barcode.Command;
import com.tw.barcode.Router;

public class BarToZip implements Command {
    private Router router;

    public BarToZip(Router router) {
        this.router = router;
    }

    @Override
    public String apply(String zip) {
        TranslateBarToZip translater = new TranslateBarToZip();
        String validateTips = translater.validate(zip);
        if (validateTips != null) {
            return "validate error:" + validateTips;
        }
        router.command = null;

        return translater.translate(zip);
    }
}
