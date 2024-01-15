package com.janjac.boot;

import com.janjac.database.migrations.UserMigration;

public class MigrationRunner {
//I was too lazy to add an auto reader and runner this takes less time, just add a static class
    public static void run(){
        UserMigration.up();
    }

}
