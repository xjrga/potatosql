CREATE PROCEDURE ${table.name}_select (
<#list table.getIterator()?filter(p -> p.isPrimaryKey()) as column>
IN v_${column.name} ${java_data_type.get_mariadb_data_type(column.typename)}<#if column?has_next>,</#if>
</#list>
)
BEGIN
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
END;
/
