package io.github.xjrga.potatosql.data.dto;

public class O_key_with_name {

    private Integer Schema_id;
    private Integer Table_id;
    private Integer Table_key_id;
    private String Table_key_name;
    private String Table_key_label;
    private Boolean Table_key_is_pk;
    private Integer Table_key_type_id;
    private String Key_type_name;
    private Integer Table_key_order;

    public O_key_with_name() {
        Schema_id = -1;
        Table_id = -1;
        Table_key_id = -1;
    }

    public void setSchema_id(Integer Schema_id) {
        this.Schema_id = Schema_id;
    }

    public void setTable_id(Integer Table_id) {
        this.Table_id = Table_id;
    }

    public void setTable_key_id(Integer Table_key_id) {
        this.Table_key_id = Table_key_id;
    }

    public void setTable_key_name(String Table_key_name) {
        this.Table_key_name = Table_key_name;
    }

    public void setTable_key_label(String Table_key_label) {
        this.Table_key_label = Table_key_label;
    }

    public void setTable_key_is_pk(Boolean Table_key_is_pk) {
        this.Table_key_is_pk = Table_key_is_pk;
    }

    public void setTable_key_type_id(Integer Table_key_type_id) {
        this.Table_key_type_id = Table_key_type_id;
    }

    public void setTable_key_order(Integer Table_key_order) {
        this.Table_key_order = Table_key_order;
    }

    public void setKey_type_name(String Key_type_name) {
        this.Key_type_name = Key_type_name;
    }

    public Integer getSchema_id() {
        return Schema_id;
    }

    public Integer getTable_id() {
        return Table_id;
    }

    public Integer getTable_key_id() {
        return Table_key_id;
    }

    public String getTable_key_name() {
        return Table_key_name;
    }

    public String getTable_key_label() {
        return Table_key_label;
    }

    public Boolean getTable_key_is_pk() {
        return Table_key_is_pk;
    }

    public Integer getTable_key_type_id() {
        return Table_key_type_id;
    }

    public Integer getTable_key_order() {
        return Table_key_order;
    }

    public String getKey_type_name() {
        return Key_type_name;
    }

    @Override
    public String toString() {
        return "Database_table_key{" + "Schema_id=" + Schema_id + ", Table_id=" + Table_id + ", Table_key_id=" + Table_key_id + ", Table_key_name=" + Table_key_name + ", Table_key_label=" + Table_key_label + ", Table_key_is_pk=" + Table_key_is_pk + ", Table_key_type_id=" + Table_key_type_id + ", Table_key_order=" + Table_key_order + '}';
    }

    public String print() {
        return "Database_table_key{" + "Schema_id=" + Schema_id + ", Table_id=" + Table_id + ", Table_key_id=" + Table_key_id + ", Table_key_name=" + Table_key_name + ", Table_key_label=" + Table_key_label + ", Table_key_is_pk=" + Table_key_is_pk + ", Table_key_type_id=" + Table_key_type_id + ", Table_key_order=" + Table_key_order + '}';
    }
}
