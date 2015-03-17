package com.kbear.noknok.service.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by allen on 3/16/15.
 */
public class RequestParameters {

    private ConcurrentHashMap<String, String> stringMap;
    private ConcurrentHashMap<String, Object> objectMap;

    public RequestParameters() {
        stringMap = new ConcurrentHashMap<>();
        objectMap = new ConcurrentHashMap<>();
    }

    public RequestParameters(Object... keysAndValues) {
        this();
        int len = keysAndValues.length;
        if (len % 2 != 0)
            throw new IllegalArgumentException("Supplied arguments must be even");
        for (int i = 0; i < len; i += 2) {
            String key = String.valueOf(keysAndValues[i]);
            String val = String.valueOf(keysAndValues[i + 1]);
            put(key, val);
        }
    }

    public void put(String key, String value) {
        stringMap.put(key, value);
    }

    public void put(String key, Object value) {
        objectMap.put(key, value);
    }

    public void put(String key, int value) {
        stringMap.put(key, String.valueOf(value));
    }

    public void put(String key, long value) {
        stringMap.put(key, String.valueOf(value));
    }

    public void remove(String key) {
        stringMap.remove(key);
    }

    public Map<String, String> getStringMap() {
        return stringMap;
    }

    public ConcurrentHashMap<String, Object> getObjectMap() {
        return objectMap;
    }
}
