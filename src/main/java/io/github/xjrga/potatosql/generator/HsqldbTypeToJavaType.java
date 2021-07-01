package io.github.xjrga.potatosql.generator;

import java.util.HashMap;

public class HsqldbTypeToJavaType {

    private HashMap map;

    public HsqldbTypeToJavaType() {

        map = new HashMap();

        map.put("IDENTITY", 0);
        map.put("INTEGER", 1);
        map.put("DOUBLE", 2);
        map.put("LONGVARCHAR", 3);
        map.put("DATE", 4);
    }

    public String getJavaType(String sqlType) {

        Integer type = (Integer) map.get(sqlType);
        String javaType;

        switch (type) {

            case 0:
            case 1:
                javaType = "Integer";
                break;
            case 2:
                javaType = "Double";
                break;
            case 3:
                javaType = "String";
                break;
            case 4:
                javaType = "Date";
                break;
            default:
                javaType = "Object";
                break;
        }

        return javaType;
    }

}
