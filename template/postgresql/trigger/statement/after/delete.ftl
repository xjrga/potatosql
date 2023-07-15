<#list data.table_iterator as table>
CREATE TRIGGER ${table.table_name}_slad_trigger
AFTER DELETE ON ${table.table_name} FOR EACH STATEMENT
EXECUTE PROCEDURE ${table.table_name}_slad_trigger_function();
/
</#list>
