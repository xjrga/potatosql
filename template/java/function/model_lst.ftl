<#list data.table_iterator as table>

import java.util.List;
import javax.swing.DefaultListModel;

public class ${table.table_name?cap_first}_pop_dlm {
    public DefaultListModel apply( List<${table.table_name?cap_first}> list ) {
        DefaultListModel model = new DefaultListModel();
        list.forEach( element
                -> {
            model.addElement( element );
        } );
        return model;
    }
}

</#list>



