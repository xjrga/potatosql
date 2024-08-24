package io.github.xjrga.potatosql.data.object;

import java.util.Objects;

public class Table {
  private Integer Schema_id;
  private Integer Table_id;
  private String Table_name;
  private Boolean Is_dependent;

  public Table() {}

  public Table(Integer Schema_id, Integer Table_id) {
    this.Schema_id = Schema_id;
    this.Table_id = Table_id;
    this.Table_name = "";
    this.Is_dependent = false;
  }

  public Table(Integer Schema_id, String Table_name) {
    this.Schema_id = Schema_id;
    this.Table_id = -1;
    this.Table_name = Table_name;
    this.Is_dependent = false;
  }

  public Table(Integer Schema_id, Integer Table_id, String Table_name) {
    this.Schema_id = Schema_id;
    this.Table_id = Table_id;
    this.Table_name = Table_name;
    this.Is_dependent = false;
  }

  public Table(Integer Schema_id, Integer Table_id, String Table_name, Boolean Is_dependent) {
    this.Schema_id = Schema_id;
    this.Table_id = Table_id;
    this.Table_name = Table_name;
    this.Is_dependent = Is_dependent;
  }

  public void setSchema_id(Integer Schema_id) {
    this.Schema_id = Schema_id;
  }

  public Integer getSchema_id() {
    return Schema_id;
  }

  public void setTable_id(Integer Table_id) {
    this.Table_id = Table_id;
  }

  public Integer getTable_id() {
    return Table_id;
  }

  public void setTable_name(String Table_name) {
    this.Table_name = Table_name;
  }

  public String getTable_name() {
    return Table_name;
  }

  public Boolean getIs_dependent() {
    return Is_dependent;
  }

  public void setIs_dependent(Boolean Is_dependent) {
    this.Is_dependent = Is_dependent;
  }

  public Table_pk getTable_pk() {
    Table_pk pk = new Table_pk(Schema_id, Table_id);
    return pk;
  }

  @Override
  public String toString() {
    return Table_name;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 59 * hash + Objects.hashCode(this.Schema_id);
    hash = 59 * hash + Objects.hashCode(this.Table_id);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    return hashCode() == obj.hashCode();
  }

  public String print() {
    return "Table{"
        + "Schema_id="
        + Schema_id
        + ", Table_id="
        + Table_id
        + ", Table_name="
        + Table_name
        + ", Is_dependent="
        + Is_dependent
        + '}';
  }
}
