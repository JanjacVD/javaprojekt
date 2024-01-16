package com.janjac.boot;


public class Boot {
    public static void main(String[] args) {
        try {
            MigrationRunner.run();
            FactoryRunner.run();
        } catch (Exception e) {

        }
    }
}
