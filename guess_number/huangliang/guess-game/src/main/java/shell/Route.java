package shell;

import core.GameMessage;
import core.StatusEnum;

/**
 * Created by lianghuang on 7/20/16.
 */
public class Route {

    private StatusEnum gameStatus;
    private CommandInvoke commandInvoke;

    public Route(CommandInvoke commandInvoke){
        this.commandInvoke = commandInvoke;
        gameStatus = StatusEnum.noStart;
    }



    public GameMessage route(String guessValue) {

        CommandEnum commandEnum = parseCommand(guessValue);
        if(commandEnum == CommandEnum.unknown) {
            return  new GameMessage(StatusEnum.unknown,"The parameter can't be parsed! Please check and input again.");
        }

        Command command = commandInvoke.invoke(commandEnum);
        GameMessage gameMessage = command.execute(guessValue);
        gameStatus = gameMessage.getStatusEnum();

        return gameMessage;
    }

    private CommandEnum parseCommand(String guessValue) {

        switch (gameStatus) {
            case noStart:
            case win:
            case failure:
                if(guessValue == null) {
                    return CommandEnum.unknown;
                }
                if(guessValue.equals("1")) {
                    return CommandEnum.start;
                } else if(guessValue.equals("2")) {
                    return CommandEnum.quit;
                } else {
                    return CommandEnum.unknown;
                }
            case playing:
                return CommandEnum.guess;
            default:
                return CommandEnum.unknown;
        }
    }
}
