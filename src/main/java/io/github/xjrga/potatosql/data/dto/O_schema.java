package io.github.xjrga.potatosql.data.dto;

public class O_schema {

    private Integer Schema_id;
    private String Schema_name;

    public O_schema() {
        Schema_id = -1;
    }

    public void setSchema_id(Integer Schema_id) {
        this.Schema_id = Schema_id;
    }

    public void setSchema_name(String Schema_name) {
        this.Schema_name = Schema_name;
    }

    public Integer getSchema_id() {
        return Schema_id;
    }

    public String getSchema_name() {
        return Schema_name;
    }

    @Override
    public String toString() {
        return Schema_name;
    }

    public String print() {
        return "O_schema{" + "Schema_id=" + Schema_id + ", Schema_name=" + Schema_name + '}';
    }

}
