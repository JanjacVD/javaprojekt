package com.janjac.abstractions;

import com.janjac.database.DbConnection;
import com.janjac.exceptions.UnavailableAtrributeException;
import com.janjac.utils.DataBuilder;
import com.janjac.utils.ModelCreator;
import com.janjac.utils.ResultSetProcessor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public abstract class Model {

    public abstract String[] fillable();
    protected int id;

    public Model(Object... args) {
        DataBuilder params = new DataBuilder(args);
        initializeAttributes(params.build());
    }
    public Model(DataBuilder builder){
        initializeAttributes(builder.build());
    }

    private void initializeAttributes(Map<String, Object> paramMap) {
        for (String attributeName : fillable()) {
            if (paramMap.containsKey(attributeName)) {
                Object value = paramMap.get(attributeName);
                setAttribute(attributeName, value);
            }
        }
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

    public void save() {
        String className = getClass().getSimpleName();
        String tableName = className.toLowerCase();

        StringBuilder insertQuery = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder values = new StringBuilder(") VALUES (");
        ArrayList<Object> params = new ArrayList<>();

        try (DbConnection connector = new DbConnection()) {
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

            int rowsAffected = connector.executeUpdate(insertQuery.toString(), params);

            System.out.println("Inserted " + rowsAffected + " row(s) into table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public static void find(int id) {
//        try{
//        String className = Thread.currentThread().getStackTrace()[2].getClassName();
//        Class<?> modelClass = Class.forName(className);
//        String query = "SELECT * FROM " + modelClass.getSimpleName().toLowerCase() + " WHERE id = " + id;
//            DbConnection connector = new DbConnection();
////            ResultSet result = connector.executeQuery(query, modelClass);
////            System.out.println(result);
//        }
//        catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
    }
}
