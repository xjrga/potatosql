<#list data.table_iterator as table>
CREATE PROCEDURE ${table.table_name}_delete_all (
--
<#list table.key_iterator?filter(o -> o.is_primary_key) as key>
IN v_${key.key_name} ${dtype.getPostgresql(key.datatype)}<#if key?has_next>,</#if>
</#list>
--
)
LANGUAGE SQL
AS $$
--
DELETE
FROM
${table.table_name}
WHERE
<#list table.key_iterator?filter(o -> o.is_primary_key) as key>
${key.key_name} = v_${key.key_name}<#if key?has_next> AND<#else>;</#if>
</#list>
--
$$;
/
</#list>
