package com.janjac.database.schema;

public class Column {
    private boolean isPrimaryKey = false;
    private boolean autoincrement = false;
    private boolean nullable = false;
    private String foreignString = "";

    private String defaultValue = "";
    private String columnName;
    ColumnType sqlType;

    Column(ColumnType _sqlType, String _columnName) {
        this.sqlType = _sqlType;
        this.columnName = _columnName;
    }

    private boolean isInt() {
        return this.sqlType.equals(ColumnType.INT) || this.sqlType.equals(ColumnType.BIGINT);
    }


    public Column primary() {
        if (isInt()) this.isPrimaryKey = true;
        return this;

    }

    public Column autoincrement() {
        if (isInt()) this.autoincrement = true;
        return this;

    }
    public Column nullable() {
        if (!isPrimaryKey) this.nullable = true;
        return this;

    }
    public Column foreign(String references, String key) {
        this.foreignString = " FOREIGN KEY (" + this.columnName + ") REFERENCES " + references + "(" + key + ")";
        return this;
    }

    public Column defaultValue(String value){
        this.defaultValue = value;
        return this;
    }
    public String build() {
        String query = this.columnName + " " + this.sqlType.getSqlType();
        if (autoincrement) query += " AUTO_INCREMENT";
        if (isPrimaryKey) query += " PRIMARY KEY";
        if (nullable) query += " NULL";
        if (!this.foreignString.isEmpty()) query += this.foreignString;
        if (!this.defaultValue.isEmpty()) query += " DEFAULT " + this.defaultValue;
        return query;
    }
}


