package com.janjac.abstractions;

public abstract class Factory<T extends Model> {
    protected abstract T create();

    public void run(int iterations){
        for(int i = 0; i<iterations; i++){
            T model = create();
            model.save();
        }
    }
}
