package io.github.xjrga.potatosql.data.object;

public class Keypair {
  private Integer Schema_id;
  private Integer Relationship_id;
  private Integer Table_id_parent;
  private Integer Key_id_parent;
  private Integer Table_id_child;
  private Integer Key_id_child;

  public Keypair() {}

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

  public void setKey_id_parent(Integer Key_id_parent) {
    this.Key_id_parent = Key_id_parent;
  }

  public Integer getKey_id_parent() {
    return Key_id_parent;
  }

  public void setTable_id_child(Integer Table_id_child) {
    this.Table_id_child = Table_id_child;
  }

  public Integer getTable_id_child() {
    return Table_id_child;
  }

  public void setKey_id_child(Integer Key_id_child) {
    this.Key_id_child = Key_id_child;
  }

  public Integer getKey_id_child() {
    return Key_id_child;
  }

  public Key_pk getParent_key() {
    Key_pk pk = new Key_pk();
    pk.setSchema_id(Schema_id);
    pk.setTable_id(Table_id_parent);
    pk.setKey_id(Key_id_parent);
    return pk;
  }

  public Key_pk getChild_key() {
    Key_pk pk = new Key_pk();
    pk.setSchema_id(Schema_id);
    pk.setTable_id(Table_id_child);
    pk.setKey_id(Key_id_child);
    return pk;
  }

  @Override
  public String toString() {
    return "O_Relationship_key_pair{Schema_id="
        + Schema_id
        + ", Relationship_id="
        + Relationship_id
        + ", Table_id_parent="
        + Table_id_parent
        + ", Key_id_parent="
        + Key_id_parent
        + ", Table_id_child="
        + Table_id_child
        + ", Key_id_child="
        + Key_id_child
        + "}";
  }

  public String print() {
    return toString();
  }
}
