<#list data.table_iterator as table>
DELETE
FROM
${table.table_name}
WHERE
--
<#list table.key_iterator?filter(o -> o.is_primary_key) as key>
${key.key_name} = v_${key.key_name}<#if key?has_next> AND<#else>;</#if>
</#list>
--
/
</#list>
