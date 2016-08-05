package core;

/**
 * Created by lianghuang on 7/28/16.
 */

//TODO: If GuessGame instance represent a round game, the status should put to GuessGame class. this class is not
// necessary.
public class Player {
    private Integer lifeValue;
    private StatusEnum status;

    public Player() {
        this.lifeValue = 6;
        this.status = StatusEnum.noStart;
    }

    public void initialize(String answer) {
        this.lifeValue = 6;
        this.status = StatusEnum.playing;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public Integer getLifeValue() {
        return lifeValue;
    }

    public void setLifeValue(Integer lifeValue) {
        this.lifeValue = lifeValue;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Integer reduceLifeValue() {
        return lifeValue > 0 ? --this.lifeValue : 0;
    }
}
