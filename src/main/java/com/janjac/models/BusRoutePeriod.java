package com.janjac.models;

import com.janjac.abstractions.Model;
import com.janjac.utils.DataBuilder;

public class BusRoutePeriod extends Model {
    protected BusRoute busRoute;

    protected long busRouteId;
    protected String startTime;
    protected String endTime;
    protected int day;

    @Override
    public String[] fillable() {
        return new String[]{"startTime", "endTime", "day", "busRoute"};
    }

    public BusRoutePeriod(Object... args) {
        super(args);
    }

    public BusRoutePeriod(DataBuilder builder) {
        super(builder);
    }
}
