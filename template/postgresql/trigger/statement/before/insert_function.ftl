<#list data.table_iterator as table>
CREATE OR REPLACE FUNCTION ${table.table_name}_slbi_trigger_function() RETURNS TRIGGER
AS $$
BEGIN
RAISE NOTICE 'Statement level before insert trigger activated';
RETURN NULL;
END;
$$ LANGUAGE 'plpgsql';
/
</#list>
