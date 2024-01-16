package com.janjac.abstractions;

import com.janjac.database.DbConnection;
import com.janjac.exceptions.UnavailableAtrributeException;
import com.janjac.properties.SealedCopy;
import com.janjac.threads.QueryExecutorThread;
import com.janjac.utils.DataBuilder;
import com.janjac.utils.DateAndTime;
import com.janjac.utils.ModelCreator;
import com.janjac.utils.ResultSetProcessor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public abstract class Model {

    public abstract String[] fillable();

    protected int id;
    protected LocalDateTime created_at;
    protected LocalDateTime updated_at;

    private SealedCopy<Model> copy;

    public Model(Object... args) {
        DataBuilder params = new DataBuilder(args);
        initializeAttributes(params.build());
    }

    public Model(DataBuilder builder) {
        initializeAttributes(builder.build());
    }

    public int getId() {
        return id;
    }
    public Model getCopy(){
        return this.copy.copy();
    }
    private void initializeAttributes(Map<String, Object> paramMap) {
        for (String attributeName : fillable()) {
            if (paramMap.containsKey(attributeName)) {
                Object value = paramMap.get(attributeName);
                setAttribute(attributeName, value);
            }
        }
        if (paramMap.containsKey("id")) {
            this.id = (int) paramMap.get("id");
        }
        if (paramMap.containsKey("created_at")) {
            this.created_at = DateAndTime.formatFromSql((Timestamp) paramMap.get("created_at"));
        }
        if (paramMap.containsKey("updated_at")) {
            this.updated_at = DateAndTime.formatFromSql((Timestamp) paramMap.get("updated_at"));
        }
        this.copy = new SealedCopy<>(this);
    }

    protected void setAttribute(String attributeName, Object value) {
        try {
            Field field = getClass().getDeclaredField(attributeName);
            field.setAccessible(true);
            field.set(this, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Error setting attribute: " + attributeName);
        }
    }

    public void update() {
        String className = getClass().getSimpleName();
        String tableName = className.toLowerCase();
        StringBuilder updateQuery = new StringBuilder("UPDATE " + tableName + " SET ");
        ArrayList<Object> params = new ArrayList<>();
        try (DbConnection connector = new DbConnection()) {
            boolean isFirst = true;
            for (String attributeName : fillable()) {
                if (!isFirst) {
                    updateQuery.append(", ");
                }
                Object attribute = getAttribute(attributeName);
                updateQuery.append(attributeName).append(" = ?");
                params.add(attribute);
                isFirst = false;
            }
            updateQuery.append(", created_at = ?");
            params.add(DateAndTime.getCurrentDatetimeAsString());
            updateQuery.append(" WHERE id = ?");
            params.add(this.id);
            Thread thread = new Thread(new QueryExecutorThread(updateQuery.toString(), params));
            thread.start();

        } catch (UnavailableAtrributeException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        String className = getClass().getSimpleName();
        String tableName = className.toLowerCase();

        StringBuilder insertQuery = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder values = new StringBuilder(") VALUES (");
        ArrayList<Object> params = new ArrayList<>();

        boolean isFirst = true;

        for (String attributeName : fillable()) {
            try {
                Object attribute = getAttribute(attributeName);

                if (!isFirst) {
                    insertQuery.append(", ");
                    values.append(", ");
                }

                insertQuery.append(attributeName);
                values.append("?");
                params.add(attribute);

            } catch (UnavailableAtrributeException e) {
                e.printStackTrace();
            }
            isFirst = false;  // Move this outside of the loop
        }

        insertQuery.append(values).append(");");
        Thread thread = new Thread(new QueryExecutorThread(insertQuery.toString(), params));
        thread.start();
    }

    public Object getAttribute(String attributeName) throws UnavailableAtrributeException {
        try {
            Field field = getClass().getDeclaredField(attributeName);
            field.setAccessible(true);
            return field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new UnavailableAtrributeException("Cannot get field " + attributeName);
        }
    }

    public static <T extends Model> ArrayList<T> where(String column, String condition, Object comparison, Class<T> clazz) {
        try {
            String className = clazz.getSimpleName();
            String query = "SELECT * FROM " + className.toLowerCase() + " WHERE " + column + " " + condition + " ?" + ";";
            try (DbConnection connector = new DbConnection()) {
                ArrayList<Object> params = new ArrayList<Object>();
                params.add(comparison);
                PreparedStatement statement = connector.prepareStatement(query, params);
                ResultSet resultSet = statement.executeQuery();
                ArrayList<Map<String, Object>> arrayOfItems = ResultSetProcessor.processResultSet(resultSet);
                return ModelCreator.createFromArrayList(arrayOfItems, clazz);
            }
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static <T extends Model> ArrayList<T> all(Class<T> clazz) {
        try {
            String className = clazz.getSimpleName();
            String query = "SELECT * FROM " + className.toLowerCase();
            try (DbConnection connector = new DbConnection()) {
                ArrayList<Object> params = new ArrayList<Object>();
                PreparedStatement statement = connector.prepareStatement(query, params);
                ResultSet resultSet = statement.executeQuery();
                ArrayList<Map<String, Object>> arrayOfItems = ResultSetProcessor.processResultSet(resultSet);
                return ModelCreator.createFromArrayList(arrayOfItems, clazz);
            }
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void delete() {
        String className = getClass().getSimpleName();
        String tableName = className.toLowerCase();

        StringBuilder deleteQuery = new StringBuilder("DELETE FROM " + tableName + " WHERE ID = ?");
        ArrayList<Object> params = new ArrayList<>();
        params.add(this.id);
        Thread thread = new Thread(new QueryExecutorThread(deleteQuery.toString(), params));
        thread.start();
    }

}
