package com.tw.barcode.command;

import com.tw.barcode.Command;
import com.tw.barcode.Router;

public class ZipToBar implements Command {
    private Router router;

    public ZipToBar(Router router) {
        this.router = router;
    }

    @Override
    public String apply(String zip) {
        TranslateZipToBar translater = new TranslateZipToBar();
        String validateTips = translater.validate(zip);
        if (validateTips != null) {
            return "validate error:" + validateTips;
        }
        router.command = null;

        return translater.translate(zip);
    }
}
