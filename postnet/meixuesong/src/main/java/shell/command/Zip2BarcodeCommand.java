package shell.command;

import core.ResponseCode;
import core.Zip2BarcodeResponse;
import core.Zip2BarcodeService;
import shell.PostNetResponse;

/**
 * Created by Xuesong Mei on 7/24/16.
 */
public class Zip2BarcodeCommand implements PostNetCommand {
    private Zip2BarcodeService service;

    public Zip2BarcodeCommand(Zip2BarcodeService service) {
        this.service = service;
    }

    @Override
    public PostNetResponse execute(String value) {
        if (value == null || value.trim().length() == 0)
            return new PostNetResponse(CommandType.ZIP2BARCODE, ResponseCode.OK, "Please enter zip:");

        Zip2BarcodeResponse zip2BarcodeResponse = service.getBarcode(value);

        if (zip2BarcodeResponse.getResponseCode() == ResponseCode.OK) {
            return new PostNetResponse(CommandType.BACK2HOME, ResponseCode.OK, zip2BarcodeResponse.getBarcode());
        } else {
            return new PostNetResponse(CommandType.ZIP2BARCODE, ResponseCode.ERROR, zip2BarcodeResponse.getMessage());
        }
    }
}
