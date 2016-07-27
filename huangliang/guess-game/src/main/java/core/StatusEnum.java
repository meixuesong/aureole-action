package core;

/**
 * Created by lianghuang on 7/20/16.
 */
public enum StatusEnum{
    noStart(0),
    playing(1),
    win(2),
    failure(3);

    private Integer status;

    StatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getValue() {
        return status;
    }

    public static StatusEnum valueOf(Integer status) {
        switch (status) {
            case 0:
                return noStart;
            case 1:
                return playing;
            case 2:
                return win;
            case 3:
                return failure;
            default:
                return noStart;
        }
    }


}
