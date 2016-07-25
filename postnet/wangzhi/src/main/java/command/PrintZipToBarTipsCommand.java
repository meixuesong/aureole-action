package command;

import enumeration.Status;
import main.CommandResponseFactory;
import vo.CommandResponse;

/**
 * Created by zbwang on 16/7/17.
 */
public class PrintZipToBarTipsCommand extends Command {

    public PrintZipToBarTipsCommand(CommandResponseFactory commandResponseFactory) {
        super(commandResponseFactory);
    }

    @Override
    public CommandResponse execute(String command){
        return getCommandResponseFactory().create("Please input zip code:", Status.zipToBar);
    }

}
