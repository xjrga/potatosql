package io.github.xjrga.potatosql.generator.java;

import java.util.HashMap;

public class Jdbc_type_to_java_type {

    private final HashMap map;

    public Jdbc_type_to_java_type() {
        map = new HashMap();
        map.put("INTEGER", 0);
        map.put("DOUBLE", 1);
        map.put("LONGVARCHAR", 2);
        map.put("DATE", 3);
        map.put("BOOLEAN", 4);
        map.put("TIME", 5);
        map.put("TIMESTAMP", 6);
    }

    public String get_java_type(String sql_type) {
        Integer type = (Integer) map.get(sql_type);
        String java_type;
        switch (type) {
            case 0:
                java_type = "Integer";
                break;
            case 1:
                java_type = "Double";
                break;
            case 2:
                java_type = "String";
                break;
            case 3:
                java_type = "Date";
                break;
            case 4:
                java_type = "Boolean";
                break;
            case 5:
                java_type = "Time";
                break;
            case 6:
                java_type = "Timestamp";
                break;
            default:
                java_type = "Object";
                break;
        }
        return java_type;
    }

}
