<#list data.table_iterator as table>
CREATE TRIGGER ${table.table_name}_slbd_trigger
BEFORE DELETE ON ${table.table_name} FOR EACH STATEMENT
EXECUTE PROCEDURE ${table.table_name}_slbd_trigger_function();
/
</#list>
