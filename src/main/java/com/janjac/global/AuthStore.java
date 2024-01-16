package com.janjac.global;

import com.janjac.models.User;

public class AuthStore {

    private User authenticatedUser = null;
    private static AuthStore instance;
    private AuthStore() {}

    public static AuthStore getInstance() {
        if (instance == null) {
            synchronized (AuthStore.class) {
                if (instance == null) {
                    instance = new AuthStore();
                }
            }
        }
        return instance;
    }


    public User getAuthenticatedUser() {
        return authenticatedUser;
    }

    public void setAuthenticatedUser(User authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }

    public void removeUser() {
        this.authenticatedUser = null;
    }
}
