package command;

import main.CommandResponseFactory;
import vo.CommandResponse;

/**
 * Created by zbwang on 16/7/24.
 */
public abstract class Command {

    private CommandResponseFactory commandResponseFactory;

    public Command(CommandResponseFactory commandResponseFactory) {
        this.commandResponseFactory = commandResponseFactory;
    }

    public abstract CommandResponse execute(String command);

    public String getName(){
        return this.getClass().getSimpleName();
    }

    public CommandResponseFactory getCommandResponseFactory() {
        return commandResponseFactory;
    }
}
