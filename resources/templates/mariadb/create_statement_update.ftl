UPDATE ${table.name}
SET
<#list table.getIterator()?filter(p -> !p.isPrimaryKey()) as column>
${column.name} = v_${column.name}<#if column?has_next>,</#if>
</#list>
WHERE
<#list table.getIterator()?filter(p -> p.isPrimaryKey()) as column>
${column.name} = v_${column.name}<#if column?has_next> AND<#else>;</#if>
</#list>
/
