package com.janjac.controllers.auth;

import com.janjac.models.User;
import com.janjac.utils.Authenticator;

public class AuthController {
    public static void main(String[] args){
        System.out.println(Authenticator.authenticate("edmond.ward", "password"));
    }
}
