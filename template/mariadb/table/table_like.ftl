<#list data.table_iterator as table>
CREATE TABLE ${table.table_name}_2
LIKE ${table.table_name};
/
</#list>
