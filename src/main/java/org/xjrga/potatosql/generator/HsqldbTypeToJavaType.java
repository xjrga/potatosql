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
        map.put("DATE", 6);
        map.put("TIME", 7);
        map.put("TIMESTAMP", 8);
        map.put("DECIMAL", 9);
        map.put("LONGVARCHAR", 10);
        map.put("BLOB", 11);
        map.put("CLOB", 12);

        /*  0	IDENTITY
            1	INTEGER
            2	DOUBLE
            3	VARCHAR
            4	BOOLEAN
            5	DATE
            6	TIME
            7	TIMESTAMP
            8	DECIMAL
            9	LONGVARCHAR
            10	BLOB
            11	CLOB
        */
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
                javaType = "Date";
                break;
            case 7:
                javaType = "Time";
                break;
            case 8:
                javaType = "Timestamp";
                break;
            case 9:
                javaType = "BigDecimal";
                break;
            case 10:
                javaType = "String";
                break;
            case 11:
                javaType = "Blob";
                break;
            case 12:
                javaType = "Clob";
                break;
            default:
                javaType = "Object";
                break;
        }

        return javaType;
    }

}
