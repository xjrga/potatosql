<#list data.table_iterator as table>
CREATE FUNCTION ${table.table_name}_count() RETURNS INTEGER
READS SQL DATA BEGIN ATOMIC
DECLARE v_count INTEGER;
SELECT COUNT(*) INTO v_count FROM ${table.table_name};
RETURN v_count;
END;
/
</#list>
