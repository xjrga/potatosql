CREATE View ${table.name}_view 
AS
SELECT
<#list table.getIterator() as column>
${column.name}<#if column?has_next>,</#if>
</#list>
FROM
${table.name};
/
