package com.janjac.database.migrations;

import com.janjac.abstractions.Migration;
import com.janjac.database.schema.Schema;

public class BusRoutePeriodMigration extends Migration {
    public static void up(){
        Schema schema = new Schema("busrouteperiod");
        schema.id();
        schema.timestamp();
        schema.integer("day");
        schema.string("startTime");
        schema.string("endTime");
        schema.integer("busRouteId").foreign("busroute", "id");
        Migration.up(schema);
    }
    public static void down(){
        Schema schema = new Schema("busrouteperiod");
        System.out.println(schema.down());
        Migration.down(schema);
    }
}