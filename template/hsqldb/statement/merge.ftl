<#list data.table_iterator as table>
MERGE INTO ${table.table_name} USING ( VALUES (
<#list table.key_iterator as key>
v_${key.key_name}<#if key?has_next>,</#if>
</#list>
) ) ON (
<#list table.key_iterator?filter(o -> o.is_primary_key) as key>
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
/
</#list>
