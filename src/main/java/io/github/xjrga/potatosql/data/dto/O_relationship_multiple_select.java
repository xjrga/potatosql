package io.github.xjrga.potatosql.data.dto;

public class O_relationship_multiple_select {

    private Integer Schema_id;
    private Integer Parent_table_id;
    private String Parent;
    private Integer Child_table_id;
    private String Child;
    private Integer Relationship_type_id;
    private Integer Relationship_id;
    private String Relationship_type_name;

    public O_relationship_multiple_select() {

    }

    public Integer getSchema_id() {
        return Schema_id;
    }

    public void setSchema_id(Integer Schema_id) {
        this.Schema_id = Schema_id;
    }

    public Integer getParent_table_id() {
        return Parent_table_id;
    }

    public void setParent_table_id(Integer Parent_table_id) {
        this.Parent_table_id = Parent_table_id;
    }

    public String getParent() {
        return Parent;
    }

    public void setParent(String Parent) {
        this.Parent = Parent;
    }

    public Integer getChild_table_id() {
        return Child_table_id;
    }

    public void setChild_table_id(Integer Child_table_id) {
        this.Child_table_id = Child_table_id;
    }

    public String getChild() {
        return Child;
    }

    public void setChild(String Child) {
        this.Child = Child;
    }

    public Integer getRelationship_type_id() {
        return Relationship_type_id;
    }

    public void setRelationship_type_id(Integer Relationship_type_id) {
        this.Relationship_type_id = Relationship_type_id;
    }

    public Integer getRelationship_id() {
        return Relationship_id;
    }

    public void setRelationship_id(Integer Relationship_id) {
        this.Relationship_id = Relationship_id;
    }

    public String getRelationship_type_name() {
        return Relationship_type_name;
    }

    public void setRelationship_type_name(String Relationship_type_name) {
        this.Relationship_type_name = Relationship_type_name;
    }

    public String print() {
        return "O_relationship_multiple_select{" + "Schema_id=" + Schema_id + ", Parent_table_id=" + Parent_table_id + ", Parent=" + Parent + ", Child_table_id=" + Child_table_id + ", Child=" + Child + ", Relationship_type_id=" + Relationship_type_id + ", Relationship_id=" + Relationship_id + ", Relationship_type_name=" + Relationship_type_name + '}';
    }
}
