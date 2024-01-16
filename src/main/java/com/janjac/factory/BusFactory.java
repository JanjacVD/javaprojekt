package com.janjac.factory;

import com.github.javafaker.Faker;
import com.janjac.abstractions.Factory;
import com.janjac.models.BusRoute;

import java.util.Random;

public class BusFactory extends Factory<BusRoute> {
    private static final String[] busRoutes = {"Vodice", "Zagreb", "Split", "Zadar", "Dubrovnik", "Vukovar", "Vinkovci", "Karlobag", "Ogulin"};
    @Override
    protected BusRoute create() {
        Faker faker = new Faker();
        String[] routes = selectRandomDistinctValue();
        return new BusRoute("startPoint",routes[0], "endPoint",routes[1], "busNumber", Integer.toString(faker.number().numberBetween(100, 999)));
    }
    private static String[] selectRandomDistinctValue() {
        Random random = new Random();
        int randomInt1 = random.nextInt(busRoutes.length);
        int randomInt2;
        do {
            randomInt2 = random.nextInt(busRoutes.length);
        } while (randomInt1 == randomInt2);

        return new String[]{busRoutes[randomInt1], busRoutes[randomInt2]};
    }
}
