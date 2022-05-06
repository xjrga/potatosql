MERGE INTO ${table.name} USING ( VALUES (
<#list table.getIterator() as column>
v_${column.name}<#if column?has_next>,</#if>
</#list>
) ) ON (
<#list table.getIterator()?filter(p -> p.isPrimaryKey()) as column>
${column.name} = v_${column.name}<#if column?has_next> AND</#if>
</#list>
)
WHEN MATCHED THEN UPDATE SET
<#list table.getIterator()?filter(p -> !p.isPrimaryKey()) as column>
${column.name} = v_${column.name}<#if column?has_next>,</#if>
</#list>
WHEN NOT MATCHED THEN INSERT VALUES
<#list table.getIterator() as column>
v_${column.name}<#if column?has_next>,<#else>;</#if>
</#list>
/
