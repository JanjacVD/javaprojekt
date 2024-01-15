package com.janjac.utils;

import java.util.HashMap;
import java.util.Map;

public class DataBuilder {
    private final Map<String, Object> data = new HashMap<>();

    public DataBuilder(Object... args) {
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("Arguments must come in pairs (key-value pairs).");
        }

        for (int i = 0; i < args.length; i += 2) {
            if (args[i] instanceof String key) {
                Object value = args[i + 1];
                data.put(key, value);
            } else {
                throw new IllegalArgumentException("Keys must be strings.");
            }
        }
    }
    public void add(String key, Object value){
        this.data.put(key, value);
    }
    public Map<String, Object> build() {
        return data;
    }}
