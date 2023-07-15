package io.github.xjrga.potatosql.generator.dot;

import java.util.HashMap;

public class Syntaxtype {
    public Syntaxtype() {
    }
    public String getHsqldb( String sql_type ) {
        HashMap<String, String> map = new HashMap();
        map.put( "INTEGER", "INTEGER" );
        map.put( "DOUBLE", "DOUBLE" );
        map.put( "DECIMAL", "DECIMAL(10,5)" );
        map.put( "VARCHAR", "VARCHAR(100)" );
        map.put( "LONGVARCHAR", "LONGVARCHAR" );
        map.put( "DATE", "DATE" );
        map.put( "TIME", "TIME" );
        map.put( "TIMESTAMP", "TIMESTAMP" );
        map.put( "BOOLEAN", "BOOLEAN" );
        return map.get( sql_type );
    }
    public String getMariadb( String sql_type ) {
        HashMap<String, String> map = new HashMap();
        map.put( "INTEGER", "INTEGER" );
        map.put( "DECIMAL", "DECIMAL(10,5)" );
        map.put( "DOUBLE", "DOUBLE" );
        map.put( "VARCHAR", "VARCHAR(200)" );
        map.put( "LONGVARCHAR", "TEXT" );
        map.put( "DATE", "DATE" );
        map.put( "TIME", "TIME" );
        map.put( "TIMESTAMP", "TIMESTAMP" );
        map.put( "BOOLEAN", "BOOLEAN" );
        return map.get( sql_type );
    }
    public String getPostgresql( String sql_type ) {
        HashMap<String, String> map = new HashMap();
        map.put( "INTEGER", "INTEGER" );
        map.put( "DECIMAL", "DECIMAL(10,5)" );
        map.put( "DOUBLE", "DOUBLE PRECISION" );
        map.put( "VARCHAR", "VARCHAR(200)" );
        map.put( "LONGVARCHAR", "TEXT" );
        map.put( "DATE", "DATE" );
        map.put( "TIME", "TIME" );
        map.put( "TIMESTAMP", "TIMESTAMP" );
        map.put( "BOOLEAN", "BOOLEAN" );
        return map.get( sql_type );
    }
    public String getOracle( String sql_type ) {
        HashMap<String, String> map = new HashMap();
        map.put( "INTEGER", "INTEGER" );
        map.put( "DECIMAL", "DECIMAL(10,5)" );
        map.put( "DOUBLE", "DOUBLE PRECISION" );
        map.put( "VARCHAR", "VARCHAR(200)" );
        map.put( "LONGVARCHAR", "LONG VARCHAR" );
        map.put( "DATE", "DATE" );
        map.put( "TIME", "TIME" );
        map.put( "TIMESTAMP", "TIMESTAMP" );
        map.put( "BOOLEAN", "BOOLEAN" );
        return map.get( sql_type );
    }
    public String getJava( String sql_type ) {
        HashMap<String, String> map = new HashMap();
        map.put( "INTEGER", "Integer" );
        map.put( "DECIMAL", "BigDecimal" );
        map.put( "DOUBLE", "Double" );
        map.put( "VARCHAR", "String" );
        map.put( "LONGVARCHAR", "String" );
        map.put( "DATE", "Date" );
        map.put( "TIME", "Time" );
        map.put( "TIMESTAMP", "Timestamp" );
        map.put( "BOOLEAN", "Boolean" );
        return map.get( sql_type );
    }
    public String getProc( String sql_type ) {
        HashMap<String, String> map = new HashMap();
        map.put( "INTEGER", "Int" );
        map.put( "DECIMAL", "BigDecimal" );
        map.put( "DOUBLE", "Double" );
        map.put( "VARCHAR", "String" );
        map.put( "LONGVARCHAR", "String" );
        map.put( "DATE", "Date" );
        map.put( "TIME", "Time" );
        map.put( "TIMESTAMP", "Timestamp" );
        map.put( "BOOLEAN", "Boolean" );
        return map.get( sql_type );
    }
}
