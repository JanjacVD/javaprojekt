package com.janjac.database;

import java.util.Map;

public class QueryBuilder {
    private static String build(Map<String, String> args){
        String keys = String.join(", ",args.keySet());
        String values = String.join(", ", args.values());
        return keys + " VALUES " + values;
    }

}
