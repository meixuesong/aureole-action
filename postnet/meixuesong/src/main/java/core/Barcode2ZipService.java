package core;

import core.exception.Barcode2ZipException;
import core.utils.CheckDigitUtil;
import core.utils.ZipUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by Xuesong Mei on 7/24/16.
 */
public class Barcode2ZipService {
    private static final Map<String, String> barcodeZipMap;
    public static final int SINGLE_BARCODE_LEN = 5;
    public static final String INVALID_BARCODE_PLEASE_TRY_AGAIN = "Invalid barcode, please try again:";

    static {
        barcodeZipMap = new HashMap<>();

        for (Entry<String, String> entry: BarcodeDictionary.getZipBarcodeMap().entrySet()) {
            barcodeZipMap.put(entry.getValue(), entry.getKey());
        }
    }

    private CheckDigitUtil checkDigitUtilUtil;
    private ZipUtil zipUtilUtil;

    public Barcode2ZipService(CheckDigitUtil checkDigitUtilUtil, ZipUtil zipUtilUtil) {
        this.checkDigitUtilUtil = checkDigitUtilUtil;
        this.zipUtilUtil = zipUtilUtil;
    }

    public Barcode2ZipResponse getZip(String barcode) {
        if (!isValidNumber(barcode)) {
            return new Barcode2ZipResponse("", ResponseCode.ERROR, INVALID_BARCODE_PLEASE_TRY_AGAIN);
        }

        try {
            String zip = barcode2Zip(barcode);

            return new Barcode2ZipResponse(zip, ResponseCode.OK, "");
        } catch (Barcode2ZipException e) {
            return new Barcode2ZipResponse("", ResponseCode.ERROR, INVALID_BARCODE_PLEASE_TRY_AGAIN);
        }
    }

    private boolean isValidNumber(String barcode) {
        if (barcode == null) return false;
        if ((barcode.length() - 2) % SINGLE_BARCODE_LEN != 0) return false;

        int i = 1;
        while (i < barcode.length() - 1) {
            String bc = barcode.substring(i, i + SINGLE_BARCODE_LEN);
            if (barcodeZipMap.get(bc) == null) return false;

            i = i + SINGLE_BARCODE_LEN;
        }

        return true;
    }

    private String barcode2Zip(String barcode) {
        StringBuilder stringBuilder = new StringBuilder();

        int i = 1;
        while (i < barcode.length() - 1) {
            String bc = barcode.substring(i, i + SINGLE_BARCODE_LEN);
            stringBuilder.append(barcodeZipMap.get(bc));

            i = i + SINGLE_BARCODE_LEN;
        }

        String zipWithCheckDigit = stringBuilder.toString();
        String checkDigit = zipWithCheckDigit.substring(zipWithCheckDigit.length() - 1);
        String zip = zipWithCheckDigit.substring(0, zipWithCheckDigit.length() - 1);

        validZip(zip);
        validCheckDigit(zip, checkDigit);

        return addHyphenIfNecessary(zip);
    }

    private void validZip(String zip) {
        if (!zipUtilUtil.isValid(zip)) throw new Barcode2ZipException();
    }

    private void validCheckDigit(String zip, String actualCD) {
        if (!actualCD.equals(String.valueOf(checkDigitUtilUtil.calc(zip))))
            throw new Barcode2ZipException();
    }

    private String addHyphenIfNecessary(String barcode) {
        if (barcode.length() == 9) {
            return barcode.substring(0, 5) + "-" + barcode.substring(5, 9);
        }

        return barcode;
    }
}
