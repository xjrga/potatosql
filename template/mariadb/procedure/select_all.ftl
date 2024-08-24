<#list data.table_iterator as table>
CREATE PROCEDURE ${table.table_name}_select_all()
--
BEGIN
--
SELECT
<#list table.key_iterator as key>
${key.key_name}<#if key?has_next>,</#if>
</#list>
FROM
${table.table_name};
--
END;
/
</#list>
