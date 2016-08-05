package shell;

import core.GameMessage;
import core.GuessGame;
import core.StatusEnum;

/**
 * Created by lianghuang on 7/20/16.
 */
public class Route {

    //TODO: using new GuessGame instance when start new game.
    private GuessGame guessGame;
    private CommandInvoke commandInvoke;
    private boolean isStarted;

    public Route(GuessGame guessGame, CommandInvoke commandInvoke){
        this.commandInvoke = commandInvoke;
        this.guessGame = guessGame;
        this.isStarted = guessGame.getPlayer().getStatus() == StatusEnum.playing;
    }

    public GameMessage route(String input) {

        Command command = commandInvoke.getCommand(isStarted, input);

        if(command == null) {
            return  new GameMessage(StatusEnum.unknown,"The parameter can't be parsed! Please check and input again.");
        }

        GameMessage gameMessage = command.execute(input);
        isStarted = gameMessage.getStatusEnum() == StatusEnum.playing;

        return gameMessage;
    }


}
