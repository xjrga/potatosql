package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.object.Keypair_mod;
import io.github.xjrga.potatosql.functions.R1;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class Relationship_keypair_pop implements R1<List<Keypair_mod>, DefaultTableModel> {
  public Vector columns;

  public Relationship_keypair_pop() {
    columns = new Vector();
    columns.add("Schema Id");
    columns.add("Relationship Id");
    columns.add("Table Id Parent");
    columns.add("Key Id Parent");
    columns.add("Table Id Child");
    columns.add("Key Id Child");
    columns.add("Parent");
    columns.add("Child");
  }

  @Override
  public DefaultTableModel apply(List<Keypair_mod> list) {
    DefaultTableModel model = new DefaultTableModel();
    Vector vector = new Vector();
    list.forEach(
        element -> {
          Vector row_vector = new Vector();
          row_vector.add(element.getSchema_id());
          row_vector.add(element.getRelationship_id());
          row_vector.add(element.getTable_id_parent());
          row_vector.add(element.getKey_id_parent());
          row_vector.add(element.getTable_id_child());
          row_vector.add(element.getKey_id_child());
          row_vector.add(element.getParent());
          row_vector.add(element.getChild());
          vector.add(row_vector);
        });
    model.setDataVector(vector, columns);
    return model;
  }
}
