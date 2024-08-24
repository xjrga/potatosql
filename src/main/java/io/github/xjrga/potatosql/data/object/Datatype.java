package io.github.xjrga.potatosql.data.object;

import java.util.Objects;

public class Datatype {
  private Integer Datatype_id;
  private String Datatype_name;

  public Datatype() {}

  public Datatype(Integer Datatype_id, String Datatype_name) {
    this.Datatype_id = Datatype_id;
    this.Datatype_name = Datatype_name;
  }

  public void setDatatype_id(Integer Datatype_id) {
    this.Datatype_id = Datatype_id;
  }

  public Integer getDatatype_id() {
    return Datatype_id;
  }

  public void setDatatype_name(String Datatype_name) {
    this.Datatype_name = Datatype_name;
  }

  public String getDatatype_name() {
    return Datatype_name;
  }

  @Override
  public String toString() {
    return Datatype_name;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 61 * hash + Objects.hashCode(this.Datatype_id);
    hash = 61 * hash + Objects.hashCode(this.Datatype_name);
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
    return hashCode() == ((Datatype) obj).hashCode();
  }
}
