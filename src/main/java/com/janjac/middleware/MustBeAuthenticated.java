package com.janjac.middleware;

import com.janjac.global.AuthStore;
import com.janjac.interfaces.MiddlewareInterface;

public class MustBeAuthenticated implements MiddlewareInterface<AuthStore> {

    @Override
    public boolean authorize(AuthStore param) {
        return param.getAuthenticatedUser() != null;
    }
}
