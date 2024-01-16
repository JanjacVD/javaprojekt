package com.janjac.interfaces;

public interface MiddlewareInterface<T> {

    boolean authorize(T param);
}
