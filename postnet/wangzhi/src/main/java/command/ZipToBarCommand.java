package command;

import core.CoreServices;
import enumeration.CodeType;
import enumeration.Status;
import main.CommandResponseFactory;
import vo.CommandResponse;

/**
 * Created by zbwang on 16/7/17.
 */
public class ZipToBarCommand extends TranslationCommand {

    public ZipToBarCommand(CommandResponseFactory commandResponseFactory, CoreServices coreServices) {
        super(commandResponseFactory, coreServices);
    }

    @Override
    public CommandResponse execute(String zipCode) {
        return getCommandResponseFactory().create(getCoreServices().translation(zipCode, CodeType.ZIP), Status.zipToBar);
    }


}
