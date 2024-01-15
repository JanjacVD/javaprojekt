package com.janjac.abstractions;

import com.janjac.database.DbConnection;
import com.janjac.database.schema.Schema;
import com.janjac.interfaces.MigrationInterface;

import java.sql.SQLException;

public abstract class Migration {
    protected static void up(Schema schema) {
        try {
            DbConnection sql = new DbConnection();
            sql.execute(schema.up());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void down(Schema schema) {
        try {
            DbConnection sql = new DbConnection();
            sql.execute(schema.down());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
