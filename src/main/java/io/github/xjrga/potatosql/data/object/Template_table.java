package io.github.xjrga.potatosql.data.object;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Template_table {
  private Integer table_id;
  private String table_name;
  private Boolean Is_dependent;
  private final List<Template_key> list = new ArrayList();

  public Template_table() {
    initialize();
    setup();
  }

  private void initialize() {}

  private void setup() {}

  public Integer getTable_id() {
    return table_id;
  }

  public void setTable_id(Integer table_id) {
    this.table_id = table_id;
  }

  public void setTable_name(String txt) {
    table_name = txt;
  }

  public String getTable_name() {
    return table_name;
  }

  public Boolean getIs_dependent() {
    return Is_dependent;
  }

  public void setIs_dependent(Boolean Is_dependent) {
    this.Is_dependent = Is_dependent;
  }

  public void add_key(Template_key key) {
    list.add(key);
  }

  public Iterator getKey_iterator() {
    return list.iterator();
  }

  public Boolean contains_primary_keys() {
    return list.stream().filter((k) -> k.getIs_primary_key()).count() > 0;
  }

  public Boolean contains_data_keys() {
    return list.stream().filter((k) -> !k.getIs_primary_key()).count() > 0;
  }

  public Boolean contains_foreign_keys() {
    return list.stream().filter((k) -> k.getIs_foreign_key()).count() > 0;
  }

  @Override
  public String toString() {
    return "Dtable{"
        + "table_id="
        + table_id
        + ", table_name="
        + table_name
        + ", Is_dependent="
        + Is_dependent
        + ", list="
        + list
        + '}';
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 19 * hash + Objects.hashCode(this.table_id);
    hash = 19 * hash + Objects.hashCode(this.table_name);
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
}
