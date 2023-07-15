<#list data.table_iterator as table>
CREATE FUNCTION ${table.table_name}_select_all()
RETURNS TABLE (<#list table.key_iterator as key>a${key.key_name} ${dtype.getHsqldb(key.datatype)}<#if key?has_next>,</#if></#list>)
READS SQL DATA
BEGIN ATOMIC
RETURN TABLE (SELECT<#list table.key_iterator as key> ${key.key_name}<#if key?has_next>, </#if></#list> FROM ${table.table_name});
END;
/
</#list>
