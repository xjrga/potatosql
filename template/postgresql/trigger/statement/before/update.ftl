<#list data.table_iterator as table>
CREATE TRIGGER ${table.table_name}_slbu_trigger
BEFORE UPDATE ON ${table.table_name} FOR EACH STATEMENT
EXECUTE PROCEDURE ${table.table_name}_slbu_trigger_function();
/
</#list>
