package com.janjac.threads;

import com.janjac.abstractions.Model;
import com.janjac.database.DbConnection;

import java.sql.SQLException;
import java.util.ArrayList;

public class QueryExecutorThread<T extends Model> implements Runnable {

    private String query;
    private ArrayList<Object> params;

    public QueryExecutorThread(String query, ArrayList<Object> params) {
        this.query = query;
        this.params = params;
    }

    public void executeUpdate() {
        try (DbConnection connector = new DbConnection()) {
            connector.executeUpdate(this.query, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        executeUpdate();
    }
}
