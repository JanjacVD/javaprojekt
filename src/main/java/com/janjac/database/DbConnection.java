package com.janjac.database;

import com.janjac.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbConnection implements AutoCloseable {
    private Connection connection;

    public DbConnection() {
        String url = "jdbc:"+Config.getKey("dbUrl") + Config.getKey("dbName");
        String dbUsername = Config.getKey("dbUsername");
        String dbPassword = Config.getKey("dbPassword");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            close();
        }
    }

    public ResultSet executeQuery(String query, ArrayList<Object> params) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setParameters(preparedStatement, params);
            return preparedStatement.executeQuery();
        }
    }

    public PreparedStatement prepareStatement(String query, ArrayList<Object> params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        setParameters(preparedStatement, params);
        return preparedStatement;
    }
    public int executeUpdate(String query, ArrayList<Object> params) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setParameters(preparedStatement, params);
            return preparedStatement.executeUpdate();
        }
    }

    public boolean execute(String query) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return preparedStatement.execute();
        }
    }

    private void setParameters(PreparedStatement preparedStatement, ArrayList<Object> params) throws SQLException {
        for (int i = 0; i < params.size(); i++) {
            preparedStatement.setObject(i + 1, params.get(i));
        }
    }

    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception appropriately
        }
    }
}
