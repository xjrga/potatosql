INSERT INTO ${table.name} (
<#list table.getIterator() as column>
${column.name}<#if column?has_next>,</#if>
</#list>
)
SELECT
<#list table.getIterator() as column>
${column.name}<#if column?has_next>,</#if>
</#list>
FROM
${table.name};
/
