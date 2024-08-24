<#list data.table_iterator as table>
<#if table.contains_data_keys()>
CREATE PROCEDURE ${table.table_name}_update (
--
<#list table.key_iterator as key>
IN v_${key.key_name} ${dtype.getMariadb(key.datatype)}<#if key?has_next>,</#if>
</#list>
--
)
--
BEGIN
--
UPDATE ${table.table_name}
SET
<#list table.key_iterator?filter(p -> !p.is_primary_key) as key>
${key.key_name} = v_${key.key_name}<#if key?has_next>,</#if>
</#list>
WHERE
<#list table.key_iterator?filter(o -> o.is_primary_key) as key>
${key.key_name} = v_${key.key_name}<#if key?has_next> AND<#else>;</#if>
</#list>
END;
/
</#if>
</#list>