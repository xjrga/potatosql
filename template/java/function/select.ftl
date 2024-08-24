<#list data.table_iterator as table>
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class ${table.table_name?cap_first}_sel {

  private final Connection connection;

  public ${table.table_name?cap_first}_sel() {
    this.connection = null;
  }

  public List<Map<String, Object>> execute(${table.table_name?cap_first} o) {
        LinkedList<Map<String, Object>> list = new LinkedList<>();        
        try ( CallableStatement proc = connection.prepareCall( "{CALL schema.procedure(<#list table.key_iterator?filter(p -> p.is_primary_key) as key>?<#if key?has_next>,</#if></#list>)}")) {
            <#list table.key_iterator?filter(p -> p.is_primary_key) as key>
            proc.set${dtype.getProc(key.datatype)}(${key?counter},o.get${key.key_name?cap_first}() );
            </#list>
            ResultSet rs = proc.executeQuery();
            while (rs.next()) {            
              Map<String, Object> row = new HashMap<>();
              <#list table.key_iterator as key>
              row.put("${key.key_name?upper_case}",rs.get${dtype.getProc(key.datatype)}(${key?counter}));
              </#list>        
             list.add(row);
            }                
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return list;
    }

  public List<Map<String, Object>> execute(<#list table.key_iterator?filter(p -> p.is_primary_key) as key>
         ${dtype.getJava(key.datatype)} ${key.key_name?lower_case}<#if key?has_next>,</#if></#list>
   ) {
        LinkedList<Map<String, Object>> list = new LinkedList<>();        
        try ( CallableStatement proc = connection.prepareCall( "{CALL schema.procedure(<#list table.key_iterator?filter(p -> p.is_primary_key) as key>?<#if key?has_next>,</#if></#list>)}")) {
            <#list table.key_iterator?filter(p -> p.is_primary_key) as key>
            proc.set${dtype.getProc(key.datatype)}(${key?counter},${key.key_name});
            </#list>
            ResultSet rs = proc.executeQuery();
            while (rs.next()) {            
              Map<String, Object> row = new HashMap<>();
              <#list table.key_iterator as key>
              row.put("${key.key_name?upper_case}",rs.get${dtype.getProc(key.datatype)}(${key?counter}));
              </#list>        
             list.add(row);
            }                
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return list;
    }

  public List<${table.table_name?cap_first}> execute_using_handler(${table.table_name?cap_first} o) {
        List<${table.table_name?cap_first}> list = null;
        BeanListHandler<${table.table_name?cap_first}> beanListHandler = new BeanListHandler<>(${table.table_name?cap_first}.class);
        try ( CallableStatement proc = connection.prepareCall( "{CALL schema.procedure(<#list table.key_iterator?filter(p -> p.is_primary_key) as key>?<#if key?has_next>,</#if></#list>)}")) {
            <#list table.key_iterator?filter(p -> p.is_primary_key) as key>
            proc.set${dtype.getProc(key.datatype)}( ${key?counter}, o.get${key.key_name?cap_first}() );
            </#list>
            ResultSet rs = proc.executeQuery();
            list = beanListHandler.handle( rs );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return list;
    }

  public List<${table.table_name?cap_first}> execute_using_handler(<#list table.key_iterator?filter(p -> p.is_primary_key) as key>
         ${dtype.getJava(key.datatype)} ${key.key_name?lower_case}<#if key?has_next>,</#if></#list>
   ) {
        List<${table.table_name?cap_first}> list = null;
        BeanListHandler<${table.table_name?cap_first}> beanListHandler = new BeanListHandler<>(${table.table_name?cap_first}.class);
        try ( CallableStatement proc = connection.prepareCall( "{CALL schema.procedure(<#list table.key_iterator?filter(p -> p.is_primary_key) as key>?<#if key?has_next>,</#if></#list>)}")) {
            <#list table.key_iterator?filter(p -> p.is_primary_key) as key>
            proc.set${dtype.getProc(key.datatype)}(${key?counter},${key.key_name});
            </#list>
            ResultSet rs = proc.executeQuery();
            list = beanListHandler.handle( rs );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return list;
    }
}
</#list>

