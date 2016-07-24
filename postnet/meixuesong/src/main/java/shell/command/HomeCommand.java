package shell.command;

import core.ResponseCode;
import shell.PostNetResponse;

/**
 * Created by Xuesong Mei on 7/24/16.
 */
public class HomeCommand implements PostNetCommand {

    private static final String message = "Please select your action: \n  1. Zip to barcode.\n  2. Barcode to zip.\n  3. Exit.";

    @Override
    public PostNetResponse execute(String value) {
        return new PostNetResponse(CommandType.HOME, ResponseCode.OK, message);
    }
}
