package com.janjac.utils;

import com.janjac.abstractions.Model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;

public class ModelCreator {
    public static<T extends Model> ArrayList<T> createFromArrayList(ArrayList<Map<String, Object>> arrayList, Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ArrayList<T> models = new ArrayList<>();
        for (Map<String, Object> rowMap : arrayList) {
            DataBuilder dataBuilder = new DataBuilder();

            for (Map.Entry<String, Object> entry : rowMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                dataBuilder.add(key, value);
            }
            T modelInstance = clazz.getDeclaredConstructor(DataBuilder.class).newInstance(dataBuilder);
            models.add(modelInstance);
        }
        return models;
    }
}
