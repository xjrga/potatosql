<#list data.table_iterator as table>
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class ${table.table_name?cap_first} {
    <#list table.key_iterator as key>
    private ${dtype.getJava(key.datatype)} ${key.key_name};
    </#list>
    public ${table.table_name?cap_first}(){}

    public ${table.table_name?cap_first}(<#list table.key_iterator as key>
        ${dtype.getJava(key.datatype)} ${key.key_name}<#if key?has_next>,</#if></#list>
    ) {
    <#list table.key_iterator as key>
        this.${key.key_name}=${key.key_name};
    </#list>       
    }
    <#list table.key_iterator as key>
    public void set${key.key_name?cap_first}(${dtype.getJava(key.datatype)} ${key.key_name}) {
        this.${key.key_name}=${key.key_name};
    }
    public ${dtype.getJava(key.datatype)} get${key.key_name?cap_first}() {
        return ${key.key_name};
    }
    </#list>
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        <#list table.key_iterator as key>
        sb.append("${key.key_name?cap_first}");
        sb.append("=");
        sb.append(${key.key_name});
        sb.append("\n");
        </#list> 
        return sb.toString();
    }
}
</#list>

