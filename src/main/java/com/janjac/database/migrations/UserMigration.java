package com.janjac.database.migrations;

import com.janjac.abstractions.Migration;
import com.janjac.database.schema.Schema;

public class UserMigration extends Migration {
    public static void up(){
        Schema schema = new Schema("user");
        schema.id();
        schema.timestamp();
        schema.string("name");
        schema.string("lastName");
        schema.string("hashedPassword");
        schema.string("username");
        schema.bool("isStudent");
        Migration.up(schema);
    }
    public static void down(){
        Schema schema = new Schema("user");
        Migration.down(schema);
    }
}
