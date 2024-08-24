package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.object.Relationship_mod;
import io.github.xjrga.potatosql.functions.R1;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class Relationship_pop implements R1<List<Relationship_mod>, DefaultTableModel> {
  @Override
  public DefaultTableModel apply(List<Relationship_mod> list) {
    DefaultTableModel model =
        new DefaultTableModel() {
          @Override
          public Class getColumnClass(int i) {
            Class returnValue = Object.class;
            switch (i) {
              case 0:
              case 1:
              case 2:
                returnValue = Integer.class;
                break;
              case 3:
                returnValue = String.class;
                break;
              case 4:
                returnValue = Integer.class;
                break;
              case 5:
                returnValue = String.class;
                break;
              case 6:
                returnValue = Boolean.class;
                break;
            }
            return returnValue;
          }

          @Override
          public boolean isCellEditable(int i, int i1) {
            return false;
          }
        };
    Vector columns = new Vector();
    columns.add("Relationship Id");
    columns.add("Schema Id");
    columns.add("Parent Table Id");
    columns.add("Parent Table Name");
    columns.add("Child Table Id");
    columns.add("Child Table Name");
    columns.add("Identifying");
    Vector vector = new Vector();
    list.forEach(
        element -> {
          Vector row_vector = new Vector();
          row_vector.add(element.getRelationship_id());
          row_vector.add(element.getSchema_id());
          row_vector.add(element.getTable_id_parent());
          row_vector.add(element.getParent());
          row_vector.add(element.getTable_id_child());
          row_vector.add(element.getChild());
          row_vector.add(element.getIs_identifying());
          vector.add(row_vector);
        });
    model.setDataVector(vector, columns);
    return model;
  }
}
