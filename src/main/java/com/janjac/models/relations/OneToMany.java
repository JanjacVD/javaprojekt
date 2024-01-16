package com.janjac.models.relations;

import com.janjac.abstractions.Model;

import java.util.ArrayList;
import java.util.List;

public class OneToMany<Child extends Model, Parent extends Model>{

    public OneToMany(){}
    public Parent fetchParent(int localId, Class<Parent> clazz){
        ArrayList<Parent> parent = Parent.where("id", "=", localId, clazz);
        if(parent.isEmpty()){
            return null;
        }
        else return parent.getFirst();
    }
    public ArrayList<Child> fetchChildren(int localId, String foreign,Class<Child> clazz){
        ArrayList<Child> children = Child.where(foreign, "=", localId, clazz);
        if(children.isEmpty()){
            return new ArrayList<Child>();
        }
        else return children;
    }
}
