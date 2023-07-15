<#list data.table_iterator as table>
CREATE TABLE ${table.table_name}
(
    <#if table.contains_primary_keys()>
        <#list table.key_iterator as key>
        ${key.key_name} ${dtype.getMariadb(key.datatype)},
        </#list>
        PRIMARY KEY (<#list table.key_iterator?filter(o -> o.is_primary_key) as key>${key.key_name}<#if key?has_next>,</#if></#list>)
    <#else>
        <#list table.key_iterator as key>
        ${key.key_name} ${dtype.getMariadb(key.datatype)}<#if key?has_next>,</#if>
        </#list>
    </#if>
);
/
</#list>
