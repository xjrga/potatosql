package io.github.xjrga.potatosql.other;

import io.github.xjrga.potatosql.data.object.Keypair;
import javax.swing.table.TableModel;

public class Relationship_keypair_finder {
    public Integer find( TableModel model, Keypair pair ) {
        Integer index = null;
        for ( int j = 0; j < model.getRowCount(); j++ ) {
            if ( pair.getSchema_id().equals( ( Integer ) model.getValueAt( j, 0 ) ) ) {
                if ( pair.getRelationship_id().equals( ( Integer ) model.getValueAt( j, 1 ) ) ) {
                    if ( pair.getTable_id_parent().equals( ( Integer ) model.getValueAt( j, 2 ) ) ) {
                        if ( pair.getKey_id_parent().equals( ( Integer ) model.getValueAt( j, 3 ) ) ) {
                            if ( pair.getTable_id_child().equals( ( Integer ) model.getValueAt( j, 4 ) ) ) {
                                if ( pair.getKey_id_child().equals( ( Integer ) model.getValueAt( j, 5 ) ) ) {
                                    index = j;
                                }
                            }
                        }
                    }
                }
            }
        }
        return index;
    }
}
