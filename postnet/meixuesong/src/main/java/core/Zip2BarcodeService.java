package core;

import core.utils.CheckDigitUtil;
import core.utils.ZipUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xuesong Mei on 7/24/16.
 */
public class Zip2BarcodeService {
    private final static String INVALID_ZIP = "%s is not a valid zip. Please try again:";
    private ZipUtil zipUtilUtil;
    private CheckDigitUtil checkDigitUtilUtil;
    private static final Map<String, String> zipBarcodeMap;

    static {
        zipBarcodeMap = new HashMap<>();
        for (Map.Entry<String, String> entry: BarcodeDictionary.getZipBarcodeMap().entrySet()) {
            zipBarcodeMap.put(entry.getKey(), entry.getValue());
        }
    }

    public Zip2BarcodeService(ZipUtil zipUtil, CheckDigitUtil checkDigitUtilUtil) {
        this.zipUtilUtil = zipUtil;
        this.checkDigitUtilUtil = checkDigitUtilUtil;
    }

    public Zip2BarcodeResponse getBarcode(String zip) {
        if (!zipUtilUtil.isValid(zip)) {
            return new Zip2BarcodeResponse("", ResponseCode.ERROR, String.format(INVALID_ZIP, zip));
        }

        String barcode = zip2Barcode(zip);
        return new Zip2BarcodeResponse(barcode, ResponseCode.OK, "");
    }

    private String zip2Barcode(String zip) {
        StringBuilder stringBuilder = new StringBuilder("|");

        for (int i = 0; i < zip.length(); i++) {
            if (zip.charAt(i) >= '0' && zip.charAt(i) <= '9') {
                String barcode = getBarcode(zip.charAt(i));
                stringBuilder.append(barcode);
            }
        }

        int checkDigit = checkDigitUtilUtil.calc(zip);
        String checkDigitBarcode = zipBarcodeMap.get(String.valueOf(checkDigit));
        stringBuilder.append(checkDigitBarcode);

        stringBuilder.append("|");
        return stringBuilder.toString();
    }

    private String getBarcode(char c) {
        return zipBarcodeMap.get(String.valueOf(c));
    }
}
