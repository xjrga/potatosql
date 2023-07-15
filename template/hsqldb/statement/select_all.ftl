<#list data.table_iterator as table>
SELECT
<#list table.key_iterator as key>
${key.key_name}<#if key?has_next>,</#if>
</#list>
FROM
${table.table_name};
/
</#list>
