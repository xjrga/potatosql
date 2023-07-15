<#list data.table_iterator as table>
CREATE PROCEDURE ${table.table_name}_insert (
<#list table.key_iterator as key>
v_${key.key_name} ${dtype.getPostgresql(key.datatype)}<#if key?has_next>,</#if>
</#list>
)
LANGUAGE SQL
AS $$
INSERT INTO ${table.table_name} (
<#list table.key_iterator as key>
${key.key_name}<#if key?has_next>,</#if>
</#list>
) VALUES (
<#list table.key_iterator as key>
v_${key.key_name}<#if key?has_next>,</#if>
</#list>
);
$$;
/
</#list>
