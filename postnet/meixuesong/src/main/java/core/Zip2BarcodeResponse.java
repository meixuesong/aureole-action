package core;

/**
 * Created by Xuesong Mei on 7/24/16.
 */
public class Zip2BarcodeResponse {
    private String barcode;
    private ResponseCode responseCode;
    private String message;

    public Zip2BarcodeResponse(String barcode, ResponseCode responseCode, String message) {
        this.barcode = barcode;
        this.responseCode = responseCode;
        this.message = message;
    }

    public String getBarcode() {
        return barcode;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public String getMessage() {
        return message;
    }
}
