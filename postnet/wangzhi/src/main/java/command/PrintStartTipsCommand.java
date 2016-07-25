package command;

import enumeration.Status;
import main.CommandResponseFactory;
import vo.CommandResponse;

/**
 * Created by zbwang on 16/7/17.
 */
public class PrintStartTipsCommand  extends Command {

    public PrintStartTipsCommand(CommandResponseFactory commandResponseFactory) {
        super(commandResponseFactory);
    }

    @Override
    public CommandResponse execute(String command){
        return getCommandResponseFactory().create("1. Translate zip code to bar code\n" +
            "2. Translate bar code to zip code\n" +
            "3. Quit\n" +
            "Please input your choices(1~3)", Status.start);
    }

}
