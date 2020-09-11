package org.xjrga.potatosql.generator;

import org.xjrga.potatosql.dataobject.DatabaseSchemaDataObject;

public class DropSchema implements Code {
    private DatabaseSchemaDataObject schemaDataObject;

    public String getCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP SCHEMA IF EXISTS");
        sb.append(" ");
        sb.append(getSchemaDataObject().getName());
        sb.append(" ");
        sb.append("CASCADE");
        sb.append(";\n\n");
        return sb.toString();
    }

    public DatabaseSchemaDataObject getSchemaDataObject() {
        return schemaDataObject;
    }

    public void setSchemaDataObject(DatabaseSchemaDataObject schemaDataObject) {
        this.schemaDataObject = schemaDataObject;
    }
//DROP SCHEMA PUBLIC CASCADE - clear all data and drop all tables
    //TRUNCATE SCHEMA PUBLIC AND COMMIT - clear all data and keep tables
    //TRUNCATE SCHEMA PUBLIC AND COMMIT NO CHECK - clear all data, keep tables, bypass referential integrity
    //TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK - clear all data, restart identities, keep tables, bypass referential integrity
}
