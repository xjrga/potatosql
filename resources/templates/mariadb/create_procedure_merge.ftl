CREATE PROCEDURE ${table.name}_merge (
<#list table.getIterator() as column>
IN v_${column.name} ${java_data_type.get_mariadb_data_type(column.typename)}<#if column?has_next>,</#if>
</#list>
)
BEGIN
INSERT INTO ${table.name} (
<#list table.getIterator() as column>
${column.name}<#if column?has_next>,</#if>
</#list>
) VALUES (
<#list table.getIterator() as column>
v_${column.name}<#if column?has_next>,</#if>
</#list>
)
ON DUPLICATE KEY UPDATE
<#list table.getIterator() as column>
${column.name} = v_${column.name}<#if column?has_next>,<#else>;</#if>
</#list>
END;
/
