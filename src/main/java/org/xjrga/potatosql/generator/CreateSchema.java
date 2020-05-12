package org.xjrga.potatosql.generator;

import org.xjrga.potatosql.dataobject.SchemaDataObject;

public class CreateSchema implements Code {
    private SchemaDataObject schemaDataObject;

    public String getCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE SCHEMA");
        sb.append(" ");
        sb.append(getSchemaDataObject().getSchemaName());
        sb.append(";\n/\n\n");
        return sb.toString();
    }

    public SchemaDataObject getSchemaDataObject() {
        return schemaDataObject;
    }

    public void setSchemaDataObject(SchemaDataObject schemaDataObject) {
        this.schemaDataObject = schemaDataObject;
    }
}
