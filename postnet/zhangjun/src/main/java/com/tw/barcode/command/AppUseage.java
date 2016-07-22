package com.tw.barcode.command;

import com.tw.barcode.Command;
import com.tw.barcode.Router;

public class AppUseage implements Command {
    private Router router;
    public AppUseage(Router router) {
        this.router = router;
    }

    @Override
    public String apply(String userInput) {
        if("1".equals(userInput)) {
            router.command = new ZipToBar(router);
            return "input zip";
        }
        if("2".equals(userInput)) {
            router.command = new BarToZip(router);
            return "input bar";
        }
        if("3".equals(userInput)) {
            System.exit(0);
        }
        return "1 zip to bar\n2 bar to zip\n3 exit";
    }

}
