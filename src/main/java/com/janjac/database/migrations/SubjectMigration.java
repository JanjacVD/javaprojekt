package com.janjac.database.migrations;

import com.janjac.abstractions.Migration;
import com.janjac.database.schema.Schema;

public class SubjectMigration extends Migration {
    protected String startTime;
    protected String endTime;
    public static void up(){
        Schema schema = new Schema("subject");
        schema.id();
        schema.string("name");
        Migration.up(schema);
    }
    public static void down(){
        Schema schema = new Schema("subject");
        System.out.println(schema.down());
        Migration.down(schema);
    }
}
