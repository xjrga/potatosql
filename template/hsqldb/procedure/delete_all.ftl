<#list data.table_iterator as table>
CREATE PROCEDURE ${table.table_name}_delete_all ()
MODIFIES SQL DATA BEGIN ATOMIC
DELETE FROM ${table.table_name};
END;
/
</#list>
