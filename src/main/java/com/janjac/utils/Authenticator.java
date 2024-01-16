package com.janjac.utils;

import com.janjac.database.DbConnection;
import com.janjac.exceptions.FailedAuthException;
import com.janjac.exceptions.UnavailableAtrributeException;
import com.janjac.models.User;

import java.util.ArrayList;

public class Authenticator {
    public static User authenticate(String username, String password) throws FailedAuthException {
        ArrayList<User> users = User.where("username", "=", username, User.class);
        if(!users.isEmpty()){
            User authAttempt = users.getFirst();
            try {
                if(PasswordEncrypt.checkPassword(password,authAttempt.getAttribute("hashedPassword").toString())){
                    return authAttempt;
                }
                throw new FailedAuthException("Invalid password");
            } catch (UnavailableAtrributeException e) {
                throw new RuntimeException(e);
            }
        }
        throw new FailedAuthException("No user by username "+username);
    }

}
