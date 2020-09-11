package org.xjrga.potatosql.dataobject;

public class TableKeyDataObject {
    private Integer SchemaId = -1;
    private Integer TableId = -1;
    private Integer KeyId = -1;
    private String Name;
    private String Label;
    private Boolean IsPK;
    private Integer TypeId = -1;
    private Integer Prcsn = -1;
    private Integer Scale = -1;
    private Integer Orden = -1;

    public TableKeyDataObject() {
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

    public Integer getKeyId() {
        return KeyId;
    }

    public void setKeyId(Integer KeyId) {
        this.KeyId = KeyId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String Label) {
        this.Label = Label;
    }

    public Boolean getIsPK() {
        return IsPK;
    }

    public void setIsPK(Boolean IsPK) {
        this.IsPK = IsPK;
    }

    public Integer getTypeId() {
        return TypeId;
    }

    public void setTypeId(Integer TypeId) {
        this.TypeId = TypeId;
    }

    public Integer getPrcsn() {
        return Prcsn;
    }

    public void setPrcsn(Integer Prcsn) {
        this.Prcsn = Prcsn;
    }

    public Integer getScale() {
        return Scale;
    }

    public void setScale(Integer Scale) {
        this.Scale = Scale;
    }

    public Integer getOrden() {
        return Orden;
    }

    public void setOrden(Integer Orden) {
        this.Orden = Orden;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Name);
        return sb.toString();
    }
}
