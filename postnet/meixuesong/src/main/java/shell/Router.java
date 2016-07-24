package shell;

import core.ResponseCode;
import shell.command.CommandType;
import shell.command.PostNetCommand;

import java.util.Map;
import java.util.Optional;

/**
 * Created by Xuesong Mei on 7/24/16.
 */
public class Router {
    private Map<String, PostNetCommand> routes;

    public Router(Map<String, PostNetCommand> routes) {
        this.routes = routes;
    }

    public PostNetResponse route(CommandType commandType, String userInputValue) {
        Optional<PostNetCommand> command = findCommand(commandType, userInputValue);

        if (!command.isPresent())
            return new PostNetResponse(CommandType.HOME, ResponseCode.ERROR, "Invalid input, please choose action.");

        if (commandType == CommandType.HOME)
            return command.get().execute("");
        else {
            return command.get().execute(userInputValue);
        }
    }

    private Optional<PostNetCommand> findCommand(CommandType commandType, String userInputValue) {
        PostNetCommand command = null;
        String key = commandType.toString();

        if (commandType == CommandType.HOME) {
            key = key + userInputValue;
        }
        command = routes.get(key);

        if (command == null) {
            return Optional.empty();
        } else {
            return Optional.of(command);
        }
    }
}
