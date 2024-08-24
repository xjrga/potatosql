<#list data.table_iterator as table>
CREATE TRIGGER ${table.table_name}_rlbi_trigger
BEFORE INSERT ON ${table.table_name}
FOR EACH ROW
WHEN (
--
<#list table.key_iterator as key>
NEW.${key.key_name} = NEW.${key.key_name}<#if key?has_next> OR </#if>
</#list>
--
)
EXECUTE PROCEDURE ${table.table_name}_rlbi_trigger_function();
/
</#list>
