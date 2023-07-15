<#list data.table_iterator as table>

import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class ${table.table_name?cap_first}_pop_dtm {
    public DefaultTableModel apply( List<${table.table_name?cap_first}> list ) {
        DefaultTableModel model = new DefaultTableModel();
        Vector columns = new Vector();
        Vector data = new Vector();
        <#list table.key_iterator as key>
        columns.add( "${key.key_name?cap_first}" );
        </#list>
        list.forEach( o -> {
            Vector row = new Vector();
            <#list table.key_iterator as key>
            row.add( o.get${key.key_name?cap_first}() );
            </#list>
            data.add( row );
        } );
        model.setDataVector( data, columns );
        return model;
    }
}

</#list>
