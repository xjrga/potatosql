package org.xjrga.potatosql.dataobject;

public class KeyTypeDataObject {

    private Integer TypeId = -1;
    private String Name;
    private Boolean PrecisionRequired;
    private Boolean ScaleRequired;

    public KeyTypeDataObject() {

    }

    public Integer getTypeId() {
        return TypeId;
    }

    public void setTypeId(Integer TypeId) {
        this.TypeId = TypeId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Name);
        return sb.toString();
    }

    public boolean equals(Object object) {
        boolean flag = false;
        if (object instanceof KeyTypeDataObject) {
            if (toString().equals(object.toString())) {
                flag = true;
            }
        }
        return flag;
    }

}


