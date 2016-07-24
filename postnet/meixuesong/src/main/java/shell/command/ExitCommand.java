package shell.command;

import core.ResponseCode;
import shell.PostNetResponse;

/**
 * Created by Xuesong Mei on 7/24/16.
 */
public class ExitCommand implements PostNetCommand {
    @Override
    public PostNetResponse execute(String value) {
        return new PostNetResponse(CommandType.EXIT, ResponseCode.OK, "Bye!");
    }
}
