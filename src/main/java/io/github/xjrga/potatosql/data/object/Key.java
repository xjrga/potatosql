package io.github.xjrga.potatosql.data.object;

public class Key {
    private Integer Schema_id;
    private Integer Table_id;
    private Integer Key_id = -1;
    private String Key_name;
    private Integer Datatype_id;
    private Integer Key_order;
    private Boolean Is_primary_key;
    private Boolean Is_foreign_key;
    public Key() {
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
    public void setIs_primary_key( Boolean Primary_key ) {
        this.Is_primary_key = Primary_key;
    }
    public Boolean getIs_primary_key() {
        return Is_primary_key;
    }
    public Boolean getIs_foreign_key() {
        return Is_foreign_key;
    }
    public void setIs_foreign_key( Boolean Is_foreign_key ) {
        this.Is_foreign_key = Is_foreign_key;
    }
    public Key_pk getTable_key_pk() {
        Key_pk pk = new Key_pk();
        pk.setSchema_id( Schema_id );
        pk.setTable_id( Table_id );
        pk.setKey_id( Key_id );
        return pk;
    }
    @Override
    public String toString() {
        return Key_name;
    }
    public String print() {
        return "Key{" + "Schema_id=" + Schema_id + ", Table_id=" + Table_id + ", Key_id=" + Key_id + ", Key_name=" + Key_name + ", Datatype_id=" + Datatype_id + ", Key_order=" + Key_order + ", Is_primary_key=" + Is_primary_key + ", Is_foreign_key=" + Is_foreign_key + '}';
    }
}
