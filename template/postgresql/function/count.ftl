<#list data.table_iterator as table>
CREATE OR REPLACE FUNCTION ${table.table_name}_count() RETURNS INTEGER
AS $$
DECLARE v_count INTEGER;
BEGIN
SELECT COUNT(*) INTO v_count FROM ${table.table_name};
return v_count;
END;
$$ LANGUAGE plpgsql;
</#list>
/


