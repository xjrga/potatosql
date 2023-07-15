<#list data.table_iterator as table>
public class ${table.table_name?cap_first} {
    <#list table.key_iterator as key>
    private ${dtype.getJava(key.datatype)} ${key.key_name};
    </#list>
    public ${table.table_name?cap_first}() {
    }
    <#list table.key_iterator as key>
    public void set${key.key_name?cap_first}(${dtype.getJava(key.datatype)} ${key.key_name}) {
        this.${key.key_name}=${key.key_name};
    }
    public ${dtype.getJava(key.datatype)} get${key.key_name?cap_first}() {
        return ${key.key_name};
    }
    </#list>
    public ${table.table_name?cap_first}_key getKey(){
       return new ${table.table_name?cap_first}_key(<#list table.key_iterator?filter(p -> p.is_primary_key) as key>${key.key_name}<#if key?has_next>, </#if></#list>);
    }
    @Override
    public String toString() {
        return "${table.table_name?cap_first}{<#list table.key_iterator as key>${key.key_name}=" + ${key.key_name}<#if key?has_next>+", </#if></#list> + "}";}
    }

</#list>