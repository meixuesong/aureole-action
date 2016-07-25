package command;

import enumeration.Status;
import main.CommandResponseFactory;
import vo.CommandResponse;

/**
 * Created by zbwang on 16/7/17.
 */
public class PrintBarToZipTipsCommand  extends Command {

    public PrintBarToZipTipsCommand(CommandResponseFactory commandResponseFactory) {
        super(commandResponseFactory);
    }

    @Override
    public CommandResponse execute(String command){
        return getCommandResponseFactory().create("Please input bar code:", Status.barToZip);
    }

}
