package io.github.xjrga.potatosql.data.object;

public class Key_mod {
    private Integer Schema_id;
    private Integer Table_id;
    private Integer Key_id;
    private String Key_name;
    private Integer Datatype_id;
    private Integer Key_order;
    private Boolean is_primary_key;
    private String Datatype_name;
    public Key_mod() {
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
    public void setKey_name( String Key_name ) {
        this.Key_name = Key_name;
    }
    public String getKey_name() {
        return Key_name;
    }
    public void setDatatype_id( Integer Datatype_id ) {
        this.Datatype_id = Datatype_id;
    }
    public Integer getDatatype_id() {
        return Datatype_id;
    }
    public void setKey_order( Integer Key_order ) {
        this.Key_order = Key_order;
    }
    public Integer getKey_order() {
        return Key_order;
    }
    public Boolean getIs_primary_key() {
        return is_primary_key;
    }
    public void setIs_primary_key( Boolean is_primary_key ) {
        this.is_primary_key = is_primary_key;
    }
    public void setDatatype_name( String Datatype_name ) {
        this.Datatype_name = Datatype_name;
    }
    public String getDatatype_name() {
        return Datatype_name;
    }
    @Override
    public String toString() {
        return "O_Table_key_mod{" + "Schema_id=" + Schema_id + ", Table_id=" + Table_id + ", Key_id=" + Key_id + ", Key_name=" + Key_name + ", Datatype_id=" + Datatype_id + ", Key_order=" + Key_order + ", Primary_key=" + is_primary_key + ", Datatype_name=" + Datatype_name + '}';
    }
}
