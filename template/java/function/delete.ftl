<#list data.table_iterator as table>
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

public class ${table.table_name?cap_first}_del {

  private final Connection connection;

  public ${table.table_name?cap_first}_del() {
    this.connection = null;
  }

  public void execute(<#list table.key_iterator?filter(p -> p.is_primary_key) as key>
    ${dtype.getJava(key.datatype)} ${key.key_name?lower_case}<#if key?has_next>,</#if></#list>
  ) {
    try (CallableStatement proc = connection.prepareCall("{CALL schema.procedure(<#list table.key_iterator?filter(p -> p.is_primary_key) as key>?<#if key?has_next>,</#if></#list>)}")) {
      <#list table.key_iterator?filter(p -> p.is_primary_key) as key>
      proc.set${dtype.getProc(key.datatype)}(${key?counter},${key.key_name?lower_case});
      </#list>
      proc.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void execute(${table.table_name?cap_first} o) {
    try (CallableStatement proc = connection.prepareCall("{CALL schema.procedure(<#list table.key_iterator?filter(p -> p.is_primary_key) as key>?<#if key?has_next>,</#if></#list>)}")) {
      <#list table.key_iterator?filter(p -> p.is_primary_key) as key>
      proc.set${dtype.getProc(key.datatype)}( ${key?counter}, o.get${key.key_name?cap_first}());
      </#list>
      proc.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
</#list>





