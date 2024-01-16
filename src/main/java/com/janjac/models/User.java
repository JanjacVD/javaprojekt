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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }
}
