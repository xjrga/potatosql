<#list data.table_iterator as table>
CREATE TRIGGER ${table.table_name}_slbi_trigger
BEFORE INSERT ON ${table.table_name} FOR EACH STATEMENT
EXECUTE PROCEDURE ${table.table_name}_slbi_trigger_function();
/
</#list>
