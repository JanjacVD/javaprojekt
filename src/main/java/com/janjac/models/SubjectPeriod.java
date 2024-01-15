package com.janjac.models;

import com.janjac.abstractions.Model;
import com.janjac.utils.DataBuilder;

public class SubjectPeriod extends Model {

    protected Subject subject;
    protected int day;
    protected int subjectId;
    protected String startTime;
    protected String endTime;

    @Override
    public String[] fillable() {
        return new String[]{"subjectId", "day", "startTime", "endTime"};
    }

    public SubjectPeriod(Object... args) {
        super(args);
    }

    public SubjectPeriod(DataBuilder builder) {
        super(builder);
    }

}
