package core;

/**
 * Created by lianghuang on 7/28/16.
 */
public class GuessGameContext {
    private Integer lifeValue;
    private StatusEnum status;
    private String answer;

    public GuessGameContext() {
        this.lifeValue = 6;
        this.status = StatusEnum.noStart;
        this.answer = null;
    }

    public void initialize(String answer) {
        this.lifeValue = 6;
        this.status = StatusEnum.playing;
        this.answer = answer;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public Integer getLifeValue() {
        return lifeValue;
    }

    public String getAnswer() {
        return answer;
    }

    public void setLifeValue(Integer lifeValue) {
        this.lifeValue = lifeValue;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
