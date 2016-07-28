package shell;

import core.GameMessage;
import core.GuessGame;
import core.StatusEnum;

/**
 * Created by lianghuang on 7/20/16.
 */
public class Route {

    private GuessGame guessGame;
    private CommandInvoke commandInvoke;

    public Route(GuessGame guessGame, CommandInvoke commandInvoke){
        this.commandInvoke = commandInvoke;
        this.guessGame = guessGame;
    }

    public GameMessage route(String guessValue) {

        Command command = parseCommand(guessValue);
        if(command == null) {
            return  new GameMessage(StatusEnum.unknown,"The parameter can't be parsed! Please check and input again.");
        }

        GameMessage gameMessage = command.execute(guessValue);

        return gameMessage;
    }

    private Command parseCommand(String guessValue) {

        Command command = null;

        switch (guessGame.getGuessGameContext().getStatus()) {
            case noStart:
            case win:
            case failure:
                if(guessValue.equals("1")) {
                    command = commandInvoke.getStartCommand();
                } else if(guessValue.equals("2")) {
                    command = commandInvoke.getQuitCommand();
                }
                break;
            case playing:
                command = commandInvoke.getGuessCommand();
                break;

        }

        return command;
    }
}
