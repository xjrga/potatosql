<#list data.table_iterator as table>

import java.util.List;
import javax.swing.DefaultComboBoxModel;

public class ${table.table_name?cap_first}_pop_dcbm {
    public DefaultComboBoxModel apply( List<${table.table_name?cap_first}> list ) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        list.forEach( element
                -> {
            model.addElement( element );
        } );
        return model;
    }
}

</#list>



