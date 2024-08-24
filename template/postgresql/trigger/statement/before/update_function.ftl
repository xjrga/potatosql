<#list data.table_iterator as table>
CREATE OR REPLACE FUNCTION ${table.table_name}_slbu_trigger_function()
RETURNS TRIGGER
AS $$
--
BEGIN
--
RAISE NOTICE 'Statement level before update activated';
--
RETURN NULL;
--
END;
--
$$ LANGUAGE 'plpgsql';
/
</#list>
