SELECT
<#list table.getIterator() as column>
${column.name}<#if column?has_next>,</#if>
</#list>
FROM
${table.name}
WHERE
<#list table.getIterator()?filter(p -> p.isPrimaryKey()) as column>
${column.name} = v_${column.name}<#if column?has_next> AND<#else>;</#if>
</#list>
/
