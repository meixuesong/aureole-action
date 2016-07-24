package shell;

import core.ResponseCode;
import shell.command.CommandType;

/**
 * Created by Xuesong Mei on 7/24/16.
 */
public class PostNetResponse {
    private CommandType commandType;
    private ResponseCode responseCode;
    private String message;

    public PostNetResponse(CommandType commandType, ResponseCode responseCode, String message) {
        this.commandType = commandType;
        this.responseCode = responseCode;
        this.message = message;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public String getMessage() {
        return message;
    }
}
