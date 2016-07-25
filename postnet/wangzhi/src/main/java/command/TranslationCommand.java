package command;

import core.CoreServices;
import main.CommandResponseFactory;

/**
 * Created by zbwang on 16/7/25.
 */
public abstract class TranslationCommand extends Command {

    private CoreServices coreServices;

    public TranslationCommand(CommandResponseFactory commandResponseFactory, CoreServices coreServices) {
        super(commandResponseFactory);
        this.coreServices = coreServices;
    }

    public CoreServices getCoreServices() {
        return coreServices;
    }
}
