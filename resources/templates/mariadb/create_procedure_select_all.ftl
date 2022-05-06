CREATE PROCEDURE ${table.name}_select_all()
BEGIN
SELECT
<#list table.getIterator() as column>
${column.name}<#if column?has_next>,</#if>
</#list>
FROM
${table.name};
END;
/
