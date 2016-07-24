package shell.command;

import core.Barcode2ZipResponse;
import core.Barcode2ZipService;
import core.ResponseCode;
import shell.PostNetResponse;

/**
 * Created by Xuesong Mei on 7/24/16.
 */
public class Barcode2ZipCommand implements PostNetCommand {
    private Barcode2ZipService service;

    public Barcode2ZipCommand(Barcode2ZipService service) {
        this.service = service;
    }

    @Override
    public PostNetResponse execute(String value) {
        if (value == null || value.trim().length() == 0)
            return new PostNetResponse(CommandType.BARCODE2ZIP, ResponseCode.OK, "Please enter barcode:");

        Barcode2ZipResponse barcode2ZipResponse = service.getZip(value);

        if (barcode2ZipResponse.getResponseCode() == ResponseCode.OK) {
            return new PostNetResponse(CommandType.BACK2HOME, ResponseCode.OK, barcode2ZipResponse.getZip());
        } else {
            return new PostNetResponse(CommandType.BARCODE2ZIP, ResponseCode.ERROR, barcode2ZipResponse.getMessage());
        }
    }
}
