package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.object.Schema;
import io.github.xjrga.potatosql.functions.R1;
import java.util.List;
import javax.swing.DefaultListModel;

public class Schema_pop
        implements R1<List<Schema>, DefaultListModel> {
    @Override
    public DefaultListModel apply( List<Schema> list ) {
        DefaultListModel model = new DefaultListModel();
        list.forEach( element
                -> {
            model.addElement( element );
        } );
        return model;
    }
}
