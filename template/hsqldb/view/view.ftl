<#list data.table_iterator as table>
CREATE View ${table.table_name}_view
AS
SELECT
<#list table.key_iterator as key>
${key.key_name}<#if key?has_next>,</#if>
</#list>
FROM
${table.table_name};
/
</#list>

