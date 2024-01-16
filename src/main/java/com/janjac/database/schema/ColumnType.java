package com.janjac.database.schema;

public enum ColumnType {
    STRING("VARCHAR(255)"), INT("INT"), BIGINT("BIGINT"), TIME("TIME"), TIMESTAMP("TIMESTAMP"), BOOLEAN("BOOLEAN");
    private final String sqlType;

    ColumnType(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getSqlType() {
        return sqlType;
    }
}