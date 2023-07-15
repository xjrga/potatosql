<#list data.table_iterator as table>
CREATE PROCEDURE ${table.table_name}_select_all()
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
<#list table.key_iterator as key>
${key.key_name}<#if key?has_next>,</#if>
</#list>
FROM
${table.table_name};
OPEN result;
END;
/
</#list>
