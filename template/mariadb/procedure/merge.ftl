<#list data.table_iterator as table>
<#if table.contains_data_keys()>
CREATE PROCEDURE ${table.table_name}_merge (
--
<#list table.key_iterator as key>
IN v_${key.key_name} ${dtype.getMariadb(key.datatype)}<#if key?has_next>,</#if>
</#list>
--
)
--
BEGIN
--
INSERT INTO ${table.table_name} (
<#list table.key_iterator as key>
${key.key_name}<#if key?has_next>,</#if>
</#list>
) VALUES (
<#list table.key_iterator as key>
v_${key.key_name}<#if key?has_next>,</#if>
</#list>
)
ON DUPLICATE KEY UPDATE
<#list table.key_iterator as key>
${key.key_name} = v_${key.key_name}<#if key?has_next>,<#else>;</#if>
</#list>
--
END;
/
</#if>
</#list>
