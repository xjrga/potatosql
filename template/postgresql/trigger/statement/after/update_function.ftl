<#list data.table_iterator as table>
CREATE OR REPLACE FUNCTION ${table.table_name}_slau_trigger_function() RETURNS TRIGGER
AS $$
BEGIN
RAISE NOTICE 'Statement level after update activated';
RETURN NULL;
END;
$$ LANGUAGE 'plpgsql';
/
</#list>
