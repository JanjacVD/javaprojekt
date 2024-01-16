package com.janjac.properties;

import com.janjac.abstractions.Model;

public record SealedCopy<T extends Model>(T copy) {
    @Override
    public T copy() {
        return copy;
    }
}
