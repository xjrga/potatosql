package io.github.xjrga.potatosql.generator;

public class Column {

    private String name;
    private String label;
    private boolean isPrimaryKey = false;
    private Integer typeid;
    private String typename;
    private Integer order;

    public Column(String name) {

        this.name = name;
    }

    public String get_name() {

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

    public String get_type_name() {

        return typename;
    }

    public void setTypeName(String typename) {

        this.typename = typename;
    }

    public Integer getOrder() {

        return order;
    }

    public void setOrder(Integer attributeorder) {

        this.order = attributeorder;
    }

}
