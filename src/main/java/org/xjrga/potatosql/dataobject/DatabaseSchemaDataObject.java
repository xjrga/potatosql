package org.xjrga.potatosql.dataobject;

public class DatabaseSchemaDataObject {
    private Integer SchemaId = -1;
    private String Name;

    public DatabaseSchemaDataObject() {
    }

    public Integer getSchemaId() {
        return SchemaId;
    }

    public void setSchemaId(Integer SchemaId) {
        this.SchemaId = SchemaId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Name);
        return sb.toString();
    }
}
