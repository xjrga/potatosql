package org.xjrga.potatosql.generator;

public class Column {
    private String name;
    private String label;
    private boolean isPrimaryKey = false;
    private Integer typeid;
    private String typename;
    private boolean isIdentity = false;
    private boolean isPrecisionRequired = false;
    private Integer precision;
    private boolean isScaleRequired = false;
    private Integer scale;
    private Integer order;

    public Column(String name, String label, boolean isPrimaryKey, Integer typeid, String typename, boolean isIdentity, boolean isPrecisionRequired, Integer precision, boolean isScaleRequired, Integer scale, Integer order) {
        this.name = name;
        this.label = label;
        this.isPrimaryKey = isPrimaryKey;
        this.typeid = typeid;
        this.typename = typename;
        this.isIdentity = isIdentity;
        this.isPrecisionRequired = isPrecisionRequired;
        this.precision = precision;
        this.isScaleRequired = isScaleRequired;
        this.scale = scale;
        this.order = order;
    }

    public Column(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(Boolean ispk) {
        this.isPrimaryKey = ispk;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeId(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTypeName() {
        return typename;
    }

    public void setTypeName(String typename) {
        this.typename = typename;
    }

    public boolean isIdentity() {
        return isIdentity;
    }

    public void setIdentity(Boolean isidentity) {
        isIdentity = isidentity;
    }

    public boolean isPrecisionRequired() {
        return isPrecisionRequired;
    }

    public void setPrecisionRequired(Boolean sizerequired) {
        isPrecisionRequired = sizerequired;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public boolean isScaleRequired() {
        return isScaleRequired;
    }

    public void setScaleRequired(Boolean scalerequired) {
        isScaleRequired = scalerequired;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer attributeorder) {
        this.order = attributeorder;
    }
}
