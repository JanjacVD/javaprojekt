package com.janjac.database.migrations;

import com.janjac.abstractions.Migration;
import com.janjac.database.schema.Schema;

public class BusRouteMigration extends Migration {
    protected String busNumber;
    protected String startPoint;
    protected String endPoint;
    public static void up(){
        Schema schema = new Schema("bus_route");
        schema.id();
        schema.string("busNumber");
        schema.string("startPoint");
        schema.string("endPoint");
        Migration.up(schema);
    }
    public static void down(){
        Schema schema = new Schema("bus_route");
        System.out.println(schema.down());
        Migration.down(schema);
    }

}
