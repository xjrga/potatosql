package io.github.xjrga.potatosql.generator.dot;

import io.github.xjrga.potatosql.functions.R1;
import java.util.List;
import javax.swing.DefaultListModel;

public class Code_populator
        implements R1<List<String>, DefaultListModel> {
    @Override
    public DefaultListModel apply( List<String> list ) {
        DefaultListModel model = new DefaultListModel();
        list.forEach( element
                -> {
            model.addElement( element );
        } );
        return model;
    }
}
