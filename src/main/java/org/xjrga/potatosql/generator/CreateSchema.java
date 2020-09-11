package org.xjrga.potatosql.generator;

import org.xjrga.potatosql.dataobject.DatabaseSchemaDataObject;

public class CreateSchema implements Code {
    private DatabaseSchemaDataObject databaseSchemaDataObject;

    public String getCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE SCHEMA");
        sb.append(" ");
        sb.append(getDatabaseSchemaDataObject().getName());
        sb.append(";\n\n");
        return sb.toString();
    }

    public DatabaseSchemaDataObject getDatabaseSchemaDataObject() {
        return databaseSchemaDataObject;
    }

    public void setDatabaseSchemaDataObject(DatabaseSchemaDataObject databaseSchemaDataObject) {
        this.databaseSchemaDataObject = databaseSchemaDataObject;
    }
}
