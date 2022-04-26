package io.github.xjrga.potatosql.data.dto;

public class O_relationship {

    private Integer Schema_id;
    private Integer Parent_table_id;
    private Integer Child_table_id;
    private Integer Relationship_id;
    private Integer Relationship_type_id;

    public O_relationship() {

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

    public void setRelationship_type_id(Integer Relationship_type_id) {
        this.Relationship_type_id = Relationship_type_id;
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

    public Integer getRelationship_type_id() {
        return Relationship_type_id;
    }

    @Override
    public String toString() {
        return "RelationshipObject{" + "Schema_id=" + Schema_id + ", Parent_table_id=" + Parent_table_id + ", Child_table_id=" + Child_table_id + ", Relationship_id=" + Relationship_id + ", Relationship_type_id=" + Relationship_type_id + '}';
    }

    public String print() {
        return "RelationshipObject{" + "Schema_id=" + Schema_id + ", Parent_table_id=" + Parent_table_id + ", Child_table_id=" + Child_table_id + ", Relationship_id=" + Relationship_id + ", Relationship_type_id=" + Relationship_type_id + '}';
    }
}
