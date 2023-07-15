<#list data.table_iterator as table>
CREATE TRIGGER ${table.table_name}_slau_trigger
AFTER UPDATE ON ${table.table_name} FOR EACH STATEMENT
EXECUTE PROCEDURE ${table.table_name}_slau_trigger_function();
/
</#list>
