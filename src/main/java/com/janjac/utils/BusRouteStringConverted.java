package com.janjac.utils;

import com.janjac.models.BusRoute;
import javafx.util.StringConverter;

public class BusRouteStringConverted extends StringConverter<BusRoute> {
    @Override
    public String toString(BusRoute busRoute) {
        return (busRoute != null) ? busRoute.getBusNumber() : "";
    }

    @Override
    public BusRoute fromString(String string) {
        // You might need to implement this method if editing of ComboBox cells is required
        return null;
    }
}
