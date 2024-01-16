package com.janjac.boot;

import com.janjac.factory.BusFactory;
import com.janjac.factory.BusRouteFactory;
import com.janjac.factory.UserFactory;

public class FactoryRunner {
    public static void run(){
//        new UserFactory().run(4);
//        new BusFactory().run(40);
        new BusRouteFactory().run(100);

    }
}
