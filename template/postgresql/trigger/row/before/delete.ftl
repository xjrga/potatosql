<#list data.table_iterator as table>
CREATE TRIGGER ${table.table_name}_rlbd_trigger
BEFORE DELETE ON ${table.table_name}
FOR EACH ROW
WHEN (
--
<#list table.key_iterator as key>
OLD.${key.key_name} = OLD.${key.key_name}<#if key?has_next> OR </#if>
</#list>
--
)
EXECUTE PROCEDURE ${table.table_name}_rlbd_trigger_function();
/
</#list>
