package com.janjac.models;

import com.janjac.abstractions.Model;
import com.janjac.utils.DataBuilder;

public class Attendance extends Model {
    @Override
    public String[] fillable() {
        return new String[]{"attended", "userId", "date"};
    }

    public Attendance(Object... args) {
        super(args);
    }

    public Attendance(DataBuilder builder) {
        super(builder);
    }
}
