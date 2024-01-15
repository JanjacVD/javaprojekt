package com.janjac.database.schema;

import java.util.ArrayList;

public class Schema {
    private String tableName;
    private ArrayList<Column> columns = new ArrayList<Column>();
    private String indexString = "";
    public Schema(String _tableName){
        this.tableName = _tableName;
    }
    public void index(String col){
        this.indexString = " INDEX(" + col + ")";
    }
    public Column string(String columnName){
        Column column = new Column(ColumnType.STRING, columnName);
        columns.add(column);
        return column;
    }

    public Column integer(String columnName){
        Column column = new Column(ColumnType.INT, columnName);
        columns.add(column);
        return column;
    }

    public Column bool(String columnName){
        Column column = new Column(ColumnType.BOOLEAN, columnName);
        columns.add(column);
        return column;
    }
    public Column bigInteger(String columnName){
        Column column = new Column(ColumnType.BIGINT, columnName);
        columns.add(column);
        return column;
    }

    public Column time(String columnName){
        Column column = new Column(ColumnType.TIME, columnName);
        columns.add(column);
        return column;
    }

    public void id(){
        Column column = new Column(ColumnType.INT, "id");
        column.autoincrement().primary();
        columns.add(column);
    }
    public void timestamp(){
        Column column = new Column(ColumnType.TIMESTAMP, "created_at");
        Column column2 = new Column(ColumnType.TIMESTAMP, "updated_at");
        column.defaultValue("CURRENT_TIMESTAMP");
        column2.defaultValue("CURRENT_TIMESTAMP");
        columns.add(column);
        columns.add(column2);
    }

    public String up(){
        String schemaString = "CREATE TABLE IF NOT EXISTS "+this.tableName+ " (";
        for(Column col : this.columns) schemaString += col.build() + ", ";
        if (schemaString.endsWith(", ")) {
            // Remove the last 2 characters
            schemaString = schemaString.substring(0, schemaString.length() - 2);
        }
        if(!indexString.isEmpty()) schemaString += indexString;
        schemaString+=")";
        return schemaString;
    }
    public String down(){
        return "DROP TABLE IF EXISTS "+ this.tableName;
    }
}
