package main;

import enumeration.Status;
import vo.CommandResponse;

/**
 * Created by zbwang on 16/7/24.
 */
public class CommandResponseFactory {
    public CommandResponse create(String msg, Status status) {
        return new CommandResponse(msg, status);
    }
}
