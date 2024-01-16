package com.janjac.utils;

import com.janjac.interfaces.MiddlewareInterface;

public class AuthorizationProcessor<T> {
    private final MiddlewareInterface<T> strategy;

    public AuthorizationProcessor(MiddlewareInterface<T> strategy) {
        this.strategy = strategy;
    }

    public boolean authorize(T param) {
        return strategy.authorize(param);
    }
}
