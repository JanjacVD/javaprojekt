package com.janjac.boot;

import com.janjac.factory.UserFactory;

public class FactoryRunner {
    public static void run(){
        new UserFactory().run(4);
    }
}
