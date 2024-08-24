<#list data.table_iterator as table>
<#if table.contains_data_keys()>
CREATE PROCEDURE ${table.table_name}_merge (
--
<#list table.key_iterator as key>
IN v_${key.key_name} ${dtype.getPostgresql(key.datatype)}<#if key?has_next>,</#if>
</#list>
--
)
LANGUAGE SQL
AS $$
--
INSERT INTO ${table.table_name} (
--
<#list table.key_iterator as key>
${key.key_name}<#if key?has_next>,</#if>
</#list>
--
) VALUES (
--
<#list table.key_iterator as key>
v_${key.key_name}<#if key?has_next>,</#if>
</#list>
--
) ON CONFLICT (
--
<#list table.key_iterator?filter(p -> p.is_primary_key) as key>
${key.key_name}<#if key?has_next>,</#if>
</#list>
--
) DO UPDATE SET
--
<#list table.key_iterator?filter(p -> !p.is_primary_key) as key>
${key.key_name} = v_${key.key_name}<#if key?has_next>,</#if>
</#list>;
--
$$;
/
</#if>
</#list>
