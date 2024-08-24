<#list data.table_iterator as table>
CREATE OR REPLACE FUNCTION ${table.table_name}_slbd_trigger_function() 
RETURNS TRIGGER
AS $$
--
BEGIN
--
RAISE NOTICE 'Statement level before delete trigger activated';
--
RETURN NULL;
--
END;
--
$$ LANGUAGE 'plpgsql';
/
</#list>
