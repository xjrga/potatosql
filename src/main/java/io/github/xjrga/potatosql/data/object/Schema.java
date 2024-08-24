package io.github.xjrga.potatosql.data.object;

import java.util.Objects;

public class Schema {
  private Integer Schema_id;
  private String Schema_name = "";

  public Schema() {}

  public Schema(Integer schema_id) {
    this.Schema_id = schema_id;
    this.Schema_name = "";
  }

  public Schema(String schema_name) {
    this.Schema_id = -1;
    this.Schema_name = schema_name;
  }

  public Schema(Integer schema_id, String schema_name) {
    this.Schema_id = schema_id;
    this.Schema_name = schema_name;
  }

  public void setSchema_id(Integer Schema_id) {
    this.Schema_id = Schema_id;
  }

  public Integer getSchema_id() {
    return Schema_id;
  }

  public void setSchema_name(String Schema_name) {
    this.Schema_name = Schema_name;
  }

  public String getSchema_name() {
    return Schema_name;
  }

  @Override
  public String toString() {
    return Schema_name;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 23 * hash + Objects.hashCode(this.Schema_id);
    hash = 23 * hash + Objects.hashCode(this.Schema_name);
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
    return "O_Database_schema{" + "Schema_id=" + Schema_id + ", Schema_name=" + Schema_name + '}';
  }
}
