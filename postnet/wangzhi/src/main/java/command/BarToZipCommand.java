package command;

import core.CoreServices;
import enumeration.CodeType;
import enumeration.Status;
import main.CommandResponseFactory;
import vo.CommandResponse;

/**
 * Created by zbwang on 16/7/17.
 */
public class BarToZipCommand extends TranslationCommand {

    public BarToZipCommand(CommandResponseFactory commandResponseFactory, CoreServices coreServices) {
        super(commandResponseFactory, coreServices);
    }

    @Override
    public CommandResponse execute(String barCode){
        return getCommandResponseFactory().create(getCoreServices().translation(barCode, CodeType.BAR), Status.barToZip);
    }

}
