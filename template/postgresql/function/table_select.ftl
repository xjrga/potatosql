<#list data.table_iterator as table>
CREATE OR REPLACE FUNCTION ${table.table_name}_select(
<#list table.key_iterator?filter(o -> o.is_primary_key) as key>
IN v_${key.key_name} ${dtype.getPostgresql(key.datatype)}<#if key?has_next>,</#if>
</#list>
) RETURNS TABLE (
<#list table.key_iterator as key>
_${key.key_name} ${dtype.getPostgresql(key.datatype)}<#if key?has_next>,</#if>
</#list>
)
AS $$
BEGIN RETURN QUERY
SELECT
<#list table.key_iterator as key>
${key.key_name}<#if key?has_next>,</#if>
</#list>
FROM
${table.table_name}
WHERE
<#list table.key_iterator?filter(o -> o.is_primary_key) as key>
${key.key_name} = v_${key.key_name}<#if key?has_next> AND<#else>;</#if>
</#list>
END;
$$ LANGUAGE 'plpgsql';
/
</#list>
