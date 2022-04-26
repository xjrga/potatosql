package io.github.xjrga.potatosql.data.dto;

public class O_relationship_key_pair {

    private Integer Schema_id;
    private Integer Parent_table_id;
    private Integer Child_table_id;
    private Integer Relationship_id;
    private Integer Parent_key_id;
    private Integer Child_key_id;

    public O_relationship_key_pair() {

    }

    public void setSchema_id(Integer Schema_id) {
        this.Schema_id = Schema_id;
    }

    public void setParent_table_id(Integer Parent_table_id) {
        this.Parent_table_id = Parent_table_id;
    }

    public void setChild_table_id(Integer Child_table_id) {
        this.Child_table_id = Child_table_id;
    }

    public void setRelationship_id(Integer Relationship_id) {
        this.Relationship_id = Relationship_id;
    }

    public void setParent_key_id(Integer Parent_key_id) {
        this.Parent_key_id = Parent_key_id;
    }

    public void setChild_key_id(Integer Child_key_id) {
        this.Child_key_id = Child_key_id;
    }

    public Integer getSchema_id() {
        return Schema_id;
    }

    public Integer getParent_table_id() {
        return Parent_table_id;
    }

    public Integer getChild_table_id() {
        return Child_table_id;
    }

    public Integer getRelationship_id() {
        return Relationship_id;
    }

    public Integer getParent_key_id() {
        return Parent_key_id;
    }

    public Integer getChild_key_id() {
        return Child_key_id;
    }

    @Override
    public String toString() {
        return "Relationship_key_pair{" + "Schema_id=" + Schema_id + ", Parent_table_id=" + Parent_table_id + ", Child_table_id=" + Child_table_id + ", Relationship_id=" + Relationship_id + ", Parent_key_id=" + Parent_key_id + ", Child_key_id=" + Child_key_id + '}';
    }

    public String print() {
        return "Relationship_key_pair{" + "Schema_id=" + Schema_id + ", Parent_table_id=" + Parent_table_id + ", Child_table_id=" + Child_table_id + ", Relationship_id=" + Relationship_id + ", Parent_key_id=" + Parent_key_id + ", Child_key_id=" + Child_key_id + '}';
    }
}
