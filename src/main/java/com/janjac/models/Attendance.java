package com.janjac.models;

import com.janjac.abstractions.Model;
import com.janjac.utils.DataBuilder;

import java.time.LocalDateTime;

public class Attendance extends Model {
    protected LocalDateTime date;
    protected User user;
    protected int userId;
    protected boolean attended;
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
