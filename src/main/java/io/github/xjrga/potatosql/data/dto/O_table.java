package io.github.xjrga.potatosql.data.dto;

public class O_table {

    private Integer Schema_id;
    private Integer Table_id;
    private String Table_name;

    public O_table() {
        Schema_id = -1;
        Table_id = -1;
    }

    public void setSchema_id(Integer Schema_id) {
        this.Schema_id = Schema_id;
    }

    public void setTable_id(Integer Table_id) {
        this.Table_id = Table_id;
    }

    public void setTable_name(String Table_name) {
        this.Table_name = Table_name;
    }

    public Integer getSchema_id() {
        return Schema_id;
    }

    public Integer getTable_id() {
        return Table_id;
    }

    public String getTable_name() {
        return Table_name;
    }

    @Override
    public String toString() {
        return Table_name;
    }

    public String print() {
        return "O_table{" + "Schema_id=" + Schema_id + ", Table_id=" + Table_id + ", Table_name=" + Table_name + '}';
    }

}
