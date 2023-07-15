<#list data.table_iterator as table>
CREATE TRIGGER ${table.table_name}_rlad_trigger
AFTER DELETE ON ${table.table_name}
FOR EACH ROW
WHEN (<#list table.key_iterator as key>OLD.${key.key_name} = OLD.${key.key_name}<#if key?has_next> AND </#if></#list> )
EXECUTE PROCEDURE ${table.table_name}_rlad_trigger_function();
/
</#list>
