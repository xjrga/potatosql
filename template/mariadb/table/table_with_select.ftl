<#list data.table_iterator as table>
CREATE TABLE ${table.table_name}_2
SELECT *
FROM
${table.table_name};
/
</#list>
