package com.janjac.utils;

import com.janjac.abstractions.Model;
import com.janjac.exceptions.UnavailableAtrributeException;
import com.janjac.properties.SealedCopy;

public class ValueChangeLog {
    public static <T extends Model> void handleUpdateChangeLog(T newModel, SealedCopy<T> oldModel, Class<T> clazz) {
        try {

            for (String attribute : newModel.fillable()) {
                Object newValue = newModel.getAttribute(attribute);
                Object copyValue = oldModel.copy().getAttribute(attribute);
                StringBuilder stringBuilder = new StringBuilder(clazz.getSimpleName()).append(" has changed attributes: \n");
                boolean hasChanges = false;

                if (!newValue.equals(copyValue)) {
                    hasChanges = true;
                    stringBuilder.append("- ").append(attribute).append(": \n")
                            .append("\t Old value: ").append(copyValue)
                            .append("\n\t New value: ").append(newValue);
                }
            }
        } catch (UnavailableAtrributeException e) {
            e.printStackTrace();
        }
    }

    public static <T extends Model> void handleSaveChangeLog(T newModel, Class<T> clazz) {
        StringBuilder stringBuilder = new StringBuilder(clazz.getSimpleName()).append(" instance was created.");

    }
}
