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
}
