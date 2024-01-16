package com.janjac.models;

import com.janjac.abstractions.Model;
import com.janjac.models.relations.OneToMany;
import com.janjac.utils.DataBuilder;

public class BusRoutePeriod extends Model {
    protected BusRoute busRoute;

    protected int busRouteId;
    protected String startTime;
    protected String endTime;
    protected int day;

    @Override
    public String[] fillable() {
        return new String[]{"startTime", "endTime", "day", "busRouteId"};
    }

    public BusRoutePeriod(Object... args) {
        super(args);
    }

    public BusRoutePeriod(DataBuilder builder) {
        super(builder);
        OneToMany<BusRoutePeriod, BusRoute> relation = new OneToMany<BusRoutePeriod, BusRoute>();
        this.busRoute = relation.fetchParent(this.busRouteId, BusRoute.class);
    }

    public BusRoute getBusRoute() {
        return busRoute;
    }

    public void setBusRoute(BusRoute busRoute) {
        this.busRoute = busRoute;
    }

    public long getBusRouteId() {
        return busRouteId;
    }

    public void setBusRouteId(int busRouteId) {
        this.busRouteId = busRouteId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
