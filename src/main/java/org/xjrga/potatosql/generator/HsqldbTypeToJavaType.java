package org.xjrga.potatosql.generator;

import java.util.HashMap;

public class HsqldbTypeToJavaType
{

    private HashMap map;


    public HsqldbTypeToJavaType()
    {

        map = new HashMap();

        map.put("IDENTITY", 1);
        map.put("INTEGER", 2);
        map.put("DOUBLE", 3);
        map.put("VARCHAR", 4);
        map.put("BOOLEAN", 5);
        map.put("BLOB", 6);
        map.put("DATE", 7);
        map.put("TIME", 8);
        map.put("TIMESTAMP", 9);

    }


    public String getJavaType(String sqlType)
    {

        Integer type = (Integer) map.get(sqlType);
        String javaType;

        switch (type)
        {

            case 1:
            case 2:
                javaType = "Integer";
                break;
            case 3:
                javaType = "Double";
                break;
            case 4:
                javaType = "String";
                break;
            case 5:
                javaType = "Boolean";
                break;
            case 6:
                javaType = "byte[]";
                break;
            case 7:
                javaType = "Date";
                break;
            case 8:
                javaType = "Time";
                break;
            case 9:
                javaType = "TimeStamp";
                break;
            default:
                javaType = "Object";
                break;
        }

        return javaType;
    }

}
