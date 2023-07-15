<#list data.table_iterator as table>
SELECT COUNT(*) FROM ${table.table_name};
/
</#list>
