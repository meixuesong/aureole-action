import core.Barcode2ZipService;
import core.utils.CheckDigitUtil;
import core.utils.ZipUtil;
import core.Zip2BarcodeService;
import shell.*;
import shell.command.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xuesong Mei on 7/24/16.
 */
public class PostNet {
    private Router router;
    private String userInputValue = "";
    private CommandType currentCommand = CommandType.HOME;

    public static void main(String[] args) {
        PostNet postNet = new PostNet();
        postNet.initialize();
        postNet.run();
    }

    private void run() {
        while (true) {
            PostNetResponse response = router.route(currentCommand, userInputValue);
            currentCommand = response.getCommandType();

            if (isNotEmpty(response.getMessage())) {
                System.out.println(response.getMessage());
            }

            if (response.getCommandType() == CommandType.EXIT) {
                break;
            } else if (response.getCommandType() == CommandType.BACK2HOME) {
                backToHome();
                continue;
            }

            userInputValue = getUserInput();
        }
    }

    private void backToHome() {
        currentCommand = CommandType.HOME;
        userInputValue = "";
    }

    private boolean isNotEmpty(String value) {
        return value != null && value.trim().length() > 0;
    }

    private String getUserInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initialize() {
        Map<String, PostNetCommand> routeMap = new HashMap<>();

        HomeCommand homeCommand = new HomeCommand();
        Zip2BarcodeCommand zip2BarcodeCommand = new Zip2BarcodeCommand(
                new Zip2BarcodeService(new ZipUtil(), new CheckDigitUtil()));
        Barcode2ZipCommand barcode2ZipCommand = new Barcode2ZipCommand(new Barcode2ZipService(new CheckDigitUtil(), new ZipUtil()));
        ExitCommand exitCommand = new ExitCommand();

        routeMap.put("HOME", homeCommand);
        routeMap.put("HOME1", zip2BarcodeCommand);
        routeMap.put("HOME2", barcode2ZipCommand);
        routeMap.put("HOME3", exitCommand);
        routeMap.put("ZIP2BARCODE", zip2BarcodeCommand);
        routeMap.put("BARCODE2ZIP", barcode2ZipCommand);

        router = new Router(routeMap);
    }
}
