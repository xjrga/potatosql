<#list data.table_iterator as table>
INSERT INTO ${table.table_name} (
<#list table.key_iterator as key>
${key.key_name}<#if key?has_next>,</#if>
</#list>
)
SELECT
<#list table.key_iterator as key>
${key.key_name}<#if key?has_next>,</#if>
</#list>
FROM
${table.table_name};
/
</#list>
