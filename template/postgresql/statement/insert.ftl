<#list data.table_iterator as table>
INSERT INTO ${table.table_name} (
--
<#list table.key_iterator as key>
${key.key_name}<#if key?has_next>,</#if>
</#list>
--
) VALUES (
--
<#list table.key_iterator as key>
v_${key.key_name}<#if key?has_next>,</#if>
</#list>
--
);
/
</#list>
