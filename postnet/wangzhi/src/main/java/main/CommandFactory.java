package main;

import command.BarToZipCommand;
import command.Command;
import command.PrintBarToZipTipsCommand;
import command.PrintStartTipsCommand;
import command.PrintZipToBarTipsCommand;
import command.ZipToBarCommand;
import core.CoreServices;

import java.util.HashMap;

/**
 * Created by zbwang on 16/7/24.
 */
public class CommandFactory {
    private HashMap<String, Command> mapping = new HashMap<String, Command>();

    public CommandFactory(CommandResponseFactory commandResponseFactory, CoreServices coreServices) {
        BarToZipCommand barToZipCommand = new BarToZipCommand(commandResponseFactory, coreServices);
        mapping.put(barToZipCommand.getName(), barToZipCommand);

        PrintBarToZipTipsCommand printBarToZipTipsCommand = new PrintBarToZipTipsCommand(commandResponseFactory);
        mapping.put(printBarToZipTipsCommand.getName(), printBarToZipTipsCommand);

        PrintStartTipsCommand printStartTipsCommand = new PrintStartTipsCommand(commandResponseFactory);
        mapping.put(printStartTipsCommand.getName(), printStartTipsCommand);

        PrintZipToBarTipsCommand printZipToBarTipsCommand = new PrintZipToBarTipsCommand(commandResponseFactory);
        mapping.put(printZipToBarTipsCommand.getName(), printZipToBarTipsCommand);

        ZipToBarCommand zipToBarCommand = new ZipToBarCommand(commandResponseFactory, coreServices);
        mapping.put(zipToBarCommand.getName(), zipToBarCommand);
    }

    public Command get(String command) {
        return mapping.get(command);
    }
}
