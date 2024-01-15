package com.janjac.boot;


public class Boot {
    public static void main(String[] args) {
        MigrationRunner.run();
        FactoryRunner.run();
    }
}
