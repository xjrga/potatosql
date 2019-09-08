package org.xjrga.potatosql.generator;

import org.xjrga.potatosql.dataobject.SchemaDataObject;

public class DropSchema implements Code
{
    private SchemaDataObject schemaDataObject;

    public String getCode()
    {
        StringBuilder sb = new StringBuilder();
        //sb.append("--");
        sb.append("DROP SCHEMA");
        sb.append(" ");
        sb.append(getSchemaDataObject().getSchemaName());
        sb.append(" ");
        sb.append("CASCADE");
        sb.append(";\n");
        //sb.append("--");
        sb.append("/\n\n");
        return sb.toString();
    }

    public SchemaDataObject getSchemaDataObject()
    {
        return schemaDataObject;
    }

    public void setSchemaDataObject(SchemaDataObject schemaDataObject)
    {
        this.schemaDataObject = schemaDataObject;
    }

    //DROP SCHEMA PUBLIC CASCADE - clear all data and drop all tables
    //TRUNCATE SCHEMA PUBLIC AND COMMIT - clear all data and keep tables
    //TRUNCATE SCHEMA PUBLIC AND COMMIT NO CHECK - clear all data, keep tables, bypass referential integrity
    //TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK - clear all data, restart identities, keep tables, bypass referential integrity
}
