package com.janjac.models;

import com.janjac.abstractions.Model;
import com.janjac.utils.DataBuilder;

public class BusRoute extends Model {
    protected String busNumber;
    protected String startPoint;
    protected String endPoint;
    @Override
    public String[] fillable() {
        return new String[]{"busNumber", "startPoint", "endPoint"};
    }

    public BusRoute(Object... args) {
        super(args);
    }

    public BusRoute(DataBuilder builder) {
        super(builder);
    }

}
