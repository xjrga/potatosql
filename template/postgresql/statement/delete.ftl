<#list data.table_iterator as table>
DELETE
FROM
${table.table_name};
/
</#list>
