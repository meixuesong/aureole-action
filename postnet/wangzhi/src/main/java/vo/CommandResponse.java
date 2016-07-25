package vo;

import enumeration.Status;

/**
 * Created by zbwang on 16/7/24.
 */
public class CommandResponse {

    private String message;
    private Status currentStatus;

    public CommandResponse(String message, Status currentStatus) {
        this.message = message;
        this.currentStatus = currentStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }
}
