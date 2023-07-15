<#list data.table_iterator as table>

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class ${table.table_name?cap_first}_sel {
    private final Connection connection = Connect.getInstance().getConnection();
    public List<${table.table_name?cap_first}> apply( ${table.table_name?cap_first} o ) {
        List<${table.table_name?cap_first}> list = null;
        BeanListHandler<${table.table_name?cap_first}> beanListHandler = new BeanListHandler<>( ${table.table_name?cap_first}.class );
        try ( CallableStatement proc = connection.prepareCall( "{CALL ${data.schema_name}.${table.table_name}_select(<#list table.key_iterator?filter(p -> p.is_primary_key) as key>?<#if key?has_next>,</#if></#list>)}")) {
            <#list table.key_iterator?filter(p -> p.is_primary_key) as key>
            proc.set${dtype.getProc(key.datatype)}( ${key?counter}, o.get${key.key_name?cap_first}() );
            </#list>
            ResultSet rs = proc.executeQuery();
            list = beanListHandler.handle( rs );
        } catch ( SQLException ex ) {
            System.out.println( ex.getMessage() );
        }
        return list;
    }
}
</#list>



