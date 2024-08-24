package io.github.xjrga.potatosql.other;

import io.github.xjrga.potatosql.data.object.Relationship;
import javax.swing.table.TableModel;

public class Relationship_finder {
  public Integer find(TableModel model, Relationship relationship) {
    Integer index = null;
    for (int j = 0; j < model.getRowCount(); j++) {
      if (relationship.getRelationship_id().equals((Integer) model.getValueAt(j, 0))) {
        if (relationship.getSchema_id().equals((Integer) model.getValueAt(j, 1))) {
          index = j;
        }
      }
    }
    return index;
  }
}
