package org.xjrga.potatosql.dataobject;

public class TableKeyKeyTypeDataObject {

    private Integer SchemaId = -1;
    private Integer TableId = -1;
    private Integer KeyId = -1;
    private String KeyName;
    private String Label;
    private Boolean IsPK;
    private Integer TypeId = -1;
    private Integer Prcsn = -1;
    private Integer Scale = -1;
    private Integer Orden = -1;
    private String TypeName;
    private Boolean PrecisionRequired;
    private Boolean ScaleRequired;
    private Boolean IsIdentity;

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

    public String getKeyName() {
        return KeyName;
    }

    public void setKeyName(String Name) {
        this.KeyName = Name;
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

    public Integer getTypeId() {
        return TypeId;
    }

    public void setTypeId(Integer TypeId) {
        this.TypeId = TypeId;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
    }

    public Boolean getPrecisionRequired() {
        return PrecisionRequired;
    }

    public void setPrecisionRequired(Boolean PrecisionRequired) {
        this.PrecisionRequired = PrecisionRequired;
    }

    public Boolean getScaleRequired() {
        return ScaleRequired;
    }

    public void setScaleRequired(Boolean ScaleRequired) {
        this.ScaleRequired = ScaleRequired;
    }

    public Boolean getIdentity() {
        return IsIdentity;
    }

    public void setIdentity(Boolean identity) {
        IsIdentity = identity;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(KeyName);
        return sb.toString();
    }

    public boolean equals(Object object) {
        boolean flag = false;
        if (object instanceof TableKeyKeyTypeDataObject) {
            if (toString().equals(object.toString())) {
                flag = true;
            }
        }
        return flag;
    }
}
