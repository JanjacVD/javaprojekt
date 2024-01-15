package com.janjac.models;

import com.janjac.abstractions.Model;
import com.janjac.utils.DataBuilder;

public class User extends Model {

    protected String name;
    protected String lastName;
    protected String username;
    protected String hashedPassword;
    protected boolean isStudent;

    @Override
    public String[] fillable() {
        return new String[]{"name", "lastName", "username", "hashedPassword", "isStudent"};
    }

    public User(Object... args) {
        super(args);
    }

    public User(DataBuilder builder) {
        super(builder);
    }
}
