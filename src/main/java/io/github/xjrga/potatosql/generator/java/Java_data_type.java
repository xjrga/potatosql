package io.github.xjrga.potatosql.generator.java;

import java.util.HashMap;

public class Java_data_type {

    public Java_data_type() {

    }

    public String get_hsqldb_data_type(String java_type) {
        HashMap<String, String> map = new HashMap();
        map.put("INTEGER", "INTEGER");
        map.put("DOUBLE", "DOUBLE");
        map.put("STRING", "LONGVARCHAR");
        map.put("DATE", "DATE");
        map.put("BOOLEAN", "BOOLEAN");
        map.put("TIME", "TIME");
        map.put("TIMESTAMP", "TIMESTAMP");
        return map.get(java_type);
    }

    public String get_mariadb_data_type(String java_type) {
        HashMap<String, String> map = new HashMap();
        map.put("INTEGER", "INT");
        map.put("DOUBLE", "DOUBLE");
        map.put("STRING", "VARCHAR(255)");
        map.put("DATE", "DATE");
        map.put("BOOLEAN", "BOOLEAN");
        map.put("TIME", "TIME");
        map.put("TIMESTAMP", "TIMESTAMP");
        return map.get(java_type);
    }

    public String get_method_data_type(String java_type) {
        HashMap<String, String> map = new HashMap();
        map.put("INTEGER", "INT");
        map.put("DOUBLE", "DOUBLE");
        map.put("STRING", "STRING");
        map.put("DATE", "DATE");
        map.put("BOOLEAN", "BOOLEAN");
        map.put("TIME", "TIME");
        map.put("TIMESTAMP", "TIMESTAMP");
        return map.get(java_type);
    }

}
