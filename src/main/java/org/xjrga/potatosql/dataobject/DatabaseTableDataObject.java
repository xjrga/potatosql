package org.xjrga.potatosql.dataobject;

public class DatabaseTableDataObject {
    private Integer SchemaId = -1;
    private Integer TableId = -1;
    private String Name;

    public DatabaseTableDataObject() {
    }

    public Integer getSchemaId() {
        return SchemaId;
    }

    public void setSchemaId(Integer SchemaId) {
        this.SchemaId = SchemaId;
    }

    public Integer getTableId() {
        return TableId;
    }

    public void setTableId(Integer TableId) {
        this.TableId = TableId;
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

    public boolean equals(Object object) {
        boolean flag = false;
        if (object instanceof DatabaseTableDataObject) {
            if (toString().equals(object.toString())) {
                flag = true;
            }
        }
        return flag;
    }
}
