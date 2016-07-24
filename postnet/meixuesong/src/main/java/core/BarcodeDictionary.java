package core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xuesong Mei on 7/24/16.
 */
public class BarcodeDictionary {
    private static final Map<String, String> zipBarcodeMap;

    static {
        zipBarcodeMap = new HashMap<>();
        zipBarcodeMap.put("0", "||:::");
        zipBarcodeMap.put("1", ":::||");
        zipBarcodeMap.put("2", "::|:|");
        zipBarcodeMap.put("3", "::||:");
        zipBarcodeMap.put("4", ":|::|");
        zipBarcodeMap.put("5", ":|:|:");
        zipBarcodeMap.put("6", ":||::");
        zipBarcodeMap.put("7", "|:::|");
        zipBarcodeMap.put("8", "|::|:");
        zipBarcodeMap.put("9", "|:|::");
    }

    public static Map<String, String> getZipBarcodeMap() {
        return Collections.unmodifiableMap(zipBarcodeMap);
    }
}
