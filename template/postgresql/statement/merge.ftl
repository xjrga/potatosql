<#list data.table_iterator as table>
<#if table.contains_data_keys()>
INSERT INTO ${table.table_name} (
<#list table.key_iterator as key>
${key.key_name}<#if key?has_next>,</#if>
</#list>
) VALUES (
<#list table.key_iterator as key>
v_${key.key_name}<#if key?has_next>,</#if>
</#list>
) ON CONFLICT (
<#list table.key_iterator?filter(p -> p.is_primary_key) as key>
${key.key_name}<#if key?has_next>,</#if>
</#list>
) DO UPDATE SET
 <#list table.key_iterator?filter(p -> !p.is_primary_key) as key>
${key.key_name} = v_${key.key_name}<#if key?has_next>,<#else>;</#if>
</#list>
<#else>
--${table.table_name}_merge
--There are no data keys.
</#if>
/
</#list>
