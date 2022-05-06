CREATE PROCEDURE ${table.name}_merge (
<#list table.getIterator() as column>
IN v_${column.name} ${java_data_type.get_hsqldb_data_type(column.typename)}<#if column?has_next>,</#if>
</#list>
)
MODIFIES SQL DATA BEGIN ATOMIC
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
END;
/
