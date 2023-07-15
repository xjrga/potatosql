package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.object.Key_mod;
import io.github.xjrga.potatosql.functions.R1;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class Tablekeys_pop
        implements R1<List<Key_mod>, DefaultTableModel> {
    @Override
    public DefaultTableModel apply( List<Key_mod> list ) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class getColumnClass( int i ) {
                Class returnValue = Object.class;
                switch ( i ) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        returnValue = Integer.class;
                        break;
                    case 4:
                        returnValue = String.class;
                        break;
                    case 5:
                        returnValue = Boolean.class;
                        break;
                    case 6:
                        returnValue = String.class;
                        break;
                    case 7:
                        returnValue = Integer.class;
                        break;
                }
                return returnValue;
            }
            @Override
            public boolean isCellEditable( int i, int i1 ) {
                return false;
            }
        };
        Vector columns = new Vector();
        columns.add( "SchemaId" );
        columns.add( "TableId" );
        columns.add( "KeyId" );
        columns.add( "TypeId" );
        columns.add( "Name" );
        columns.add( "Primary Key" );
        columns.add( "Type" );
        columns.add( "Order" );
        Vector vector = new Vector();
        list.forEach( element
                -> {
            Vector row_vector = new Vector();
            row_vector.add( element.getSchema_id() );
            row_vector.add( element.getTable_id() );
            row_vector.add( element.getKey_id() );
            row_vector.add( element.getDatatype_id() );
            row_vector.add( element.getKey_name() );
            row_vector.add( element.getIs_primary_key() );
            row_vector.add( element.getDatatype_name() );
            row_vector.add( element.getKey_order() );
            vector.add( row_vector );
        } );
        model.setDataVector( vector, columns );
        return model;
    }
}
