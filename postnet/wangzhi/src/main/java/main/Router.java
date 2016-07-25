package main;

import enumeration.Status;
import org.apache.commons.lang3.StringUtils;
import vo.CommandResponse;

/**
 * Created by zbwang on 16/7/17.
 */
public class Router {

    private CommandFactory commandFactory;
    private Status status;

    public Router(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public String route(String command) {
        String commandName = null;
        if (status == null) {
            commandName = "PrintStartTipsCommand";
        } else if (status.equals(Status.start)) {
            if (StringUtils.equals(command, "1")) {
                commandName = "PrintZipToBarTipsCommand";
            } else if (StringUtils.equals(command, "2")) {
                commandName = "PrintBarToZipTipsCommand";
            } else if (StringUtils.equals(command, "3")) {
                System.exit(0);
            }
        } else if (status.equals(Status.barToZip)) {
            commandName = "BarToZipCommand";
        } else if (status.equals(Status.zipToBar)) {
            commandName = "ZipToBarCommand";
        }
        if (StringUtils.isEmpty(commandName)) {
            return "Please give right input";
        }

        CommandResponse response = commandFactory.get(commandName).execute(command);
        this.status = response.getCurrentStatus();
        return response.getMessage();

    }
}
