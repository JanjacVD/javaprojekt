package com.janjac.database.migrations;

import com.janjac.abstractions.Migration;
import com.janjac.database.schema.Schema;

public class BusRouteMigration extends Migration {
    public static void up(){
        Schema schema = new Schema("busroute");
        schema.id();
        schema.timestamp();
        schema.string("busNumber");
        schema.string("startPoint");
        schema.string("endPoint");
        Migration.up(schema);
    }
    public static void down(){
        Schema schema = new Schema("busroute");
        System.out.println(schema.down());
        Migration.down(schema);
    }

}
