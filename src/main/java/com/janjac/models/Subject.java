package com.janjac.models;

import com.janjac.abstractions.Model;
import com.janjac.utils.DataBuilder;

public class Subject extends Model {
    protected String name;

    @Override
    public String[] fillable() {
        return new String[]{"name"};
    }

    public Subject(Object... args) {
        super(args);
    }

    public Subject(DataBuilder builder) {
        super(builder);
    }
}
