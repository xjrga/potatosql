<#list data.table_iterator as table>
CREATE OR REPLACE FUNCTION ${table.table_name}_rlbd_trigger_function()
RETURNS TRIGGER
AS $$
--
BEGIN
--
<#list table.key_iterator as key>
OLD.${key.key_name} = OLD.${key.key_name};
</#list>
--
RAISE NOTICE 'Old values are %',<#list table.key_iterator as key>OLD.${key.key_name}<#if key?has_next> || ',' || </#if></#list>;
RAISE NOTICE 'New values are %',<#list table.key_iterator as key>NEW.${key.key_name}<#if key?has_next> || ',' || </#if></#list>;
--
RETURN OLD;
--
END;
--
$$ LANGUAGE 'plpgsql';
/
</#list>
