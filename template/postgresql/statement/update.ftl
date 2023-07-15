<#list data.table_iterator as table>
<#if table.contains_data_keys()>
UPDATE ${table.table_name}
SET
<#list table.key_iterator?filter(o -> !o.is_primary_key) as key>
${key.key_name} = v_${key.key_name}<#if key?has_next>,</#if>
</#list>
WHERE
<#list table.key_iterator?filter(o -> o.is_primary_key) as key>
${key.key_name} = v_${key.key_name}<#if key?has_next> AND<#else>;</#if>
</#list>
<#else>
--${table.table_name}_update
--There are no data keys
</#if>
/
</#list>
