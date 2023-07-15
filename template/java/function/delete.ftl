<#list data.table_iterator as table>

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ${table.table_name?cap_first}_del {
    private final Connection connection = Connect.getInstance().getConnection();
    public void apply( ${table.table_name?cap_first} o ) {
        try ( CallableStatement proc = connection.prepareCall( "{CALL ${data.schema_name}.${table.table_name}_delete(<#list table.key_iterator?filter(p -> p.is_primary_key) as key>?<#if key?has_next>,</#if></#list>)}")) {
            <#list table.key_iterator?filter(p -> p.is_primary_key) as key>
            proc.set${dtype.getProc(key.datatype)}( ${key?counter}, o.get${key.key_name?cap_first}() );
            </#list>
            proc.execute();
        } catch ( SQLException ex ) {
            System.out.println( ex.getMessage() );
        }
    }
}

</#list>




