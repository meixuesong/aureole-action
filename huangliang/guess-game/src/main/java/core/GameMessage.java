package core;

/**
 * Created by lianghuang on 7/20/16.
 */
public class GameMessage {

    private Integer lifeValue;
    private String calculateResult;
    private StatusEnum statusEnum;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getCalculateResult() {
        return calculateResult;
    }

    public void setCalculateResult(String calculateResult) {
        this.calculateResult = calculateResult;
    }

    public Integer getLifeValue() {
        return lifeValue;
    }

    public void setLifeValue(Integer lifeValue) {
        this.lifeValue = lifeValue;
    }

    private String errorMessage;



}
