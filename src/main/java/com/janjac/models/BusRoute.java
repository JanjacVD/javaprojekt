package com.janjac.models;

import com.janjac.abstractions.Model;
import com.janjac.utils.DataBuilder;

public class BusRoute extends Model {
    protected String busNumber;
    protected String startPoint;
    protected String endPoint;

    @Override
    public String toString() {
        return this.busNumber;
    }

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

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }
}
