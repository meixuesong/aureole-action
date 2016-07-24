package core;

/**
 * Created by Xuesong Mei on 7/24/16.
 */
public class Barcode2ZipResponse {
    private String zip;
    private ResponseCode responseCode;
    private String message;

    public Barcode2ZipResponse(String zip, ResponseCode responseCode, String message) {
        this.zip = zip;
        this.responseCode = responseCode;
        this.message = message;
    }

    public String getZip() {
        return zip;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public String getMessage() {
        return message;
    }
}
