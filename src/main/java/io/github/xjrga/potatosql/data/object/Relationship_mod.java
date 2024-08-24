package io.github.xjrga.potatosql.data.object;

public class Relationship_mod {
  private Integer Schema_id;
  private Integer Relationship_id;
  private Integer Table_id_parent;
  private Integer Table_id_child;
  private Boolean Is_identifying;
  private String Parent;
  private String Child;

  public Relationship_mod() {}

  public void setSchema_id(Integer Schema_id) {
    this.Schema_id = Schema_id;
  }

  public Integer getSchema_id() {
    return Schema_id;
  }

  public void setRelationship_id(Integer Relationship_id) {
    this.Relationship_id = Relationship_id;
  }

  public Integer getRelationship_id() {
    return Relationship_id;
  }

  public void setTable_id_parent(Integer Table_id_parent) {
    this.Table_id_parent = Table_id_parent;
  }

  public Integer getTable_id_parent() {
    return Table_id_parent;
  }

  public void setTable_id_child(Integer Table_id_child) {
    this.Table_id_child = Table_id_child;
  }

  public Integer getTable_id_child() {
    return Table_id_child;
  }

  public void setIs_identifying(Boolean Identifying) {
    this.Is_identifying = Identifying;
  }

  public Boolean getIs_identifying() {
    return Is_identifying;
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

  public Relationship get_relationship_object() {
    Relationship o = new Relationship();
    o.setSchema_id(Schema_id);
    o.setTable_id_parent(Table_id_parent);
    o.setTable_id_child(Table_id_child);
    o.setRelationship_id(Relationship_id);
    o.setIs_identifying(Is_identifying);
    return o;
  }

  @Override
  public String toString() {
    return "O_Relationship_mod{"
        + "Schema_id="
        + Schema_id
        + ", Relationship_id="
        + Relationship_id
        + ", Table_id_parent="
        + Table_id_parent
        + ", Table_id_child="
        + Table_id_child
        + ", Identifying="
        + Is_identifying
        + ", Parent="
        + Parent
        + ", Child="
        + Child
        + '}';
  }
}
