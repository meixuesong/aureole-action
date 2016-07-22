package com.tw.barcode;

import java.io.*;

public class Bootstrap {

    public static void main(String[] args) throws IOException {
        Router router = new Router();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = new PrintStream(System.out);

        String line = null;
        while(true) {
            String reply = router.request(line);
            out.println(reply);

            line = in.readLine();
        }
    }
}
