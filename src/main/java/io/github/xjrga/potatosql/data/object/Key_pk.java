package io.github.xjrga.potatosql.data.object;

public class Key_pk {
    private Integer Schema_id;
    private Integer Table_id;
    private Integer Key_id;
    public Key_pk() {
    }
    public void setSchema_id( Integer Schema_id ) {
        this.Schema_id = Schema_id;
    }
    public Integer getSchema_id() {
        return Schema_id;
    }
    public void setTable_id( Integer Table_id ) {
        this.Table_id = Table_id;
    }
    public Integer getTable_id() {
        return Table_id;
    }
    public void setKey_id( Integer Key_id ) {
        this.Key_id = Key_id;
    }
    public Integer getKey_id() {
        return Key_id;
    }
    @Override
    public String toString() {
        return "O_Table_key_pk{Schema_id=" + Schema_id + ", Table_id=" + Table_id + ", Key_id=" + Key_id + "}";
    }
    public String print() {
        return toString();
    }
}
