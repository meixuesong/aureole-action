package core;

/**
 * Created by Xuesong Mei on 7/24/16.
 */
public enum ResponseCode {
    OK("OK"),
    ERROR("ERROR");

    ResponseCode(String value) {
        this.value = value;
    }

    private String value;

    @Override
    public String toString() {
        return value;
    }
}
