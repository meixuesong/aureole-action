package shell.command;

import shell.PostNetResponse;

/**
 * Created by Xuesong Mei on 7/24/16.
 */
public interface PostNetCommand {
    PostNetResponse execute(String value);
}
