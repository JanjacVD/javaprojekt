package com.janjac.database.migrations;

import com.janjac.abstractions.Migration;
import com.janjac.database.schema.Schema;
import com.janjac.models.Subject;

public class SubjectPeriodMigration extends Migration{
    public static void up(){
        Schema schema = new Schema("subject_period");
        schema.id();
        schema.string("startTime");
        schema.string("endTime");
        schema.integer("day");
        schema.integer("subjectId").foreign("subject", "id");
        Migration.up(schema);
    }
    public static void down(){
        Schema schema = new Schema("subject_period");
        System.out.println(schema.down());
        Migration.down(schema);
    }
}
