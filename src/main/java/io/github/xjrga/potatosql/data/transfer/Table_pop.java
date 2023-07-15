package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.object.Table;
import io.github.xjrga.potatosql.functions.R1;
import java.util.List;
import javax.swing.DefaultListModel;

public class Table_pop
        implements R1<List<Table>, DefaultListModel> {
    @Override
    public DefaultListModel apply( List<Table> list ) {
        DefaultListModel model = new DefaultListModel();
        list.forEach( element
                -> {
            model.addElement( element );
        } );
        return model;
    }
}
