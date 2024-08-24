<#list data.table_iterator as table>
<#if table.contains_data_keys()>
CREATE PROCEDURE ${table.table_name}_merge (
--
<#list table.key_iterator as key>
IN v_${key.key_name} ${dtype.getHsqldb(key.datatype)}<#if key?has_next>,</#if>
</#list>
--
)
--
MODIFIES SQL DATA
BEGIN ATOMIC
--
MERGE INTO ${table.table_name} USING ( VALUES (
<#list table.key_iterator as key>
v_${key.key_name}<#if key?has_next>,</#if>
</#list>
) ) ON (
<#list table.key_iterator?filter(p -> p.is_primary_key) as key>
${key.key_name} = v_${key.key_name}<#if key?has_next> AND</#if>
</#list>
)
WHEN MATCHED THEN UPDATE SET
<#list table.key_iterator?filter(p -> !p.is_primary_key) as key>
${key.key_name} = v_${key.key_name}<#if key?has_next>,</#if>
</#list>
WHEN NOT MATCHED THEN INSERT VALUES
<#list table.key_iterator as key>
v_${key.key_name}<#if key?has_next>,<#else>;</#if>
</#list>
--
END;
/
</#if>
</#list>

