package io.github.xjrga.potatosql.data.dto;

/**
 *
 * @author jr
 */
public class O_select_only_names {

    private String Parent;
    private String Child;
    private Integer Relationship_id;
    private Integer Relationship_type_id;
    private String Parent_key_name;
    private String Child_key_name;

    public O_select_only_names() {

    }

    public String getParent() {
        return Parent;
    }

    public void setParent(String Parent) {
        this.Parent = Parent;
    }

    public String getChild() {
        return Child;
    }

    public void setChild(String Child) {
        this.Child = Child;
    }

    public Integer getRelationship_id() {
        return Relationship_id;
    }

    public void setRelationship_id(Integer Relationship_id) {
        this.Relationship_id = Relationship_id;
    }

    public Integer getRelationship_type_id() {
        return Relationship_type_id;
    }

    public void setRelationship_type_id(Integer Relationship_type_id) {
        this.Relationship_type_id = Relationship_type_id;
    }

    public String getParent_key_name() {
        return Parent_key_name;
    }

    public void setParent_key_name(String Parent_key_name) {
        this.Parent_key_name = Parent_key_name;
    }

    public String getChild_key_name() {
        return Child_key_name;
    }

    public void setChild_key_name(String Child_key_name) {
        this.Child_key_name = Child_key_name;
    }

    @Override
    public String toString() {
        return "O_select_only_names{" + "Parent=" + Parent + ", Child=" + Child + ", Relationship_id=" + Relationship_id + ", Relationship_type_id=" + Relationship_type_id + ", Parent_key_name=" + Parent_key_name + ", Child_key_name=" + Child_key_name + '}';
    }
}
