package shell;

/**
 * Created by lianghuang on 7/20/16.
 */
public class CommandInvoke {


    public Command invoke(CommandEnum commandEnum) {

        switch (commandEnum) {
            case start:
                return new StartCommand();
            case guess:
                return new GuessCommand();
            case quit:
                return new QuitCommand();
            default:
                break;
        }

        return null;
    }
}
