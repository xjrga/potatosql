<#list data.table_iterator as table>
CREATE FUNCTION ${table.table_name}_count()
RETURNS INT
--
BEGIN
--
DECLARE v_count INT;
--
SELECT COUNT(*) INTO v_count
FROM
${table.table_name};
--
RETURN v_count;
--
END;
/
</#list>
