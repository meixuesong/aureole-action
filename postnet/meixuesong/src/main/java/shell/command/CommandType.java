package shell.command;

/**
 * Created by Xuesong Mei on 7/24/16.
 */
public enum CommandType {
    HOME("HOME"),
    ZIP2BARCODE("ZIP2BARCODE"),
    BARCODE2ZIP("BARCODE2ZIP"),
    BACK2HOME("BACK2HOME"),
    EXIT("EXIT");

    private final String value;

    CommandType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
