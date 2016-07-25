package core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lianghuang on 7/17/16.
 */
public class SessionStorage {

    private static Map<String,String> storage = new HashMap<String, String>();

    public static void put(String key, String value) {
        storage.put(key, value);
    }

    public static String get(String key) {
        return storage.get(key);
    }
}
