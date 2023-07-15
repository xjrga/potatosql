<#list data.table_iterator as table>
INSERT INTO ${table.table_name} (
<#list table.key_iterator as key>
${key.key_name}<#if key?has_next>,</#if>
</#list>
) VALUES (
<#list table.key_iterator as key>
v_${key.key_name}<#if key?has_next>,</#if>
</#list>
)
ON DUPLICATE KEY UPDATE
<#list table.key_iterator as key>
${key.key_name} = v_${key.key_name}<#if key?has_next>,<#else>;</#if>
</#list>
/
</#list>
