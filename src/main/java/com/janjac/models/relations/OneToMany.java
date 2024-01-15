package com.janjac.models.relations;

import com.janjac.abstractions.Model;

import java.util.List;

public abstract class OneToMany<T1 extends Model, T2 extends Model>{

    String local;
    String foreign;
    String tableLocal;
    String tableForeign;
    public OneToMany(String local, String foreign, String tableLocal, String tableForeign){
        this.local = local;
        this.foreign = foreign;
        this.tableLocal = tableLocal;
        this.tableForeign = tableForeign;
    }

    public void fetchRelation(int localId){
        String query = "SELECT * FROM " + tableForeign +
                " WHERE " + foreign + " = "+ localId;
    }
}
