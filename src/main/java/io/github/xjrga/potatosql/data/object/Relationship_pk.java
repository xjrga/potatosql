package io.github.xjrga.potatosql.data.object;

public class Relationship_pk {
    private Integer Schema_id;
    private Integer Relationship_id;
    private Integer Table_id_parent;
    private Integer Table_id_child;
    public Relationship_pk() {
    }
    public void setSchema_id( Integer Schema_id ) {
        this.Schema_id = Schema_id;
    }
    public Integer getSchema_id() {
        return Schema_id;
    }
    public void setRelationship_id( Integer Relationship_id ) {
        this.Relationship_id = Relationship_id;
    }
    public Integer getRelationship_id() {
        return Relationship_id;
    }
    public void setTable_id_parent( Integer Table_id_parent ) {
        this.Table_id_parent = Table_id_parent;
    }
    public Integer getTable_id_parent() {
        return Table_id_parent;
    }
    public void setTable_id_child( Integer Table_id_child ) {
        this.Table_id_child = Table_id_child;
    }
    public Integer getTable_id_child() {
        return Table_id_child;
    }
    @Override
    public String toString() {
        return "O_Relationship_min{" + "Schema_id=" + Schema_id + ", Relationship_id=" + Relationship_id + ", Table_id_parent=" + Table_id_parent + ", Table_id_child=" + Table_id_child + '}';
    }
    public String print() {
        return toString();
    }
}
