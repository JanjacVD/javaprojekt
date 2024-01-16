package com.janjac.factory;

import com.github.javafaker.Faker;
import com.janjac.abstractions.Factory;
import com.janjac.models.BusRoutePeriod;

import java.text.DecimalFormat;
import java.util.Random;


public class BusRouteFactory extends Factory<BusRoutePeriod> {
    Faker faker = new Faker();
    Random random = new Random();
    final int[] daysOfWeek = {0,1,2,3,4,5,6};
    @Override
    protected BusRoutePeriod create() {
        int randomDay = daysOfWeek[random.nextInt(0, daysOfWeek.length)];

        int randomBusRoute = random.nextInt(40);
        String timeFrom = randomTime("00:00");
        String timeTo = randomTime(timeFrom);
        return new BusRoutePeriod("startTime",timeFrom ,"endTime",timeTo, "day",randomDay ,"busRouteId", randomBusRoute);
    }
    private String randomTime(String minTime) {
        String[] minTimeParts = minTime.split(":");
        int minHours = Integer.parseInt(minTimeParts[0]);
        int minMinutes = Integer.parseInt(minTimeParts[1]);
        int hours = minHours + random.nextInt(24 - minHours);
        int minutes = minMinutes + random.nextInt(((60 - minMinutes) / 5) + 1) * 5;
        if(minutes == 60) {
            minutes = 0;
            hours += 1;
        }
        DecimalFormat df = new DecimalFormat("00");
        String formattedHours = df.format(hours);
        String formattedMinutes = df.format(minutes);
        return formattedHours + ":" + formattedMinutes;
    }
}
