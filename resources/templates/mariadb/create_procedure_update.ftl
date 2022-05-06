CREATE PROCEDURE ${table.name}_update (
<#list table.getIterator() as column>
IN v_${column.name} ${java_data_type.get_mariadb_data_type(column.typename)}<#if column?has_next>,</#if>
</#list>
)
BEGIN
UPDATE ${table.name}
SET
<#list table.getIterator()?filter(p -> !p.isPrimaryKey()) as column>
${column.name} = v_${column.name}<#if column?has_next>,</#if>
</#list>
WHERE
<#list table.getIterator()?filter(p -> p.isPrimaryKey()) as column>
${column.name} = v_${column.name}<#if column?has_next> AND<#else>;</#if>
</#list>
END;
/
