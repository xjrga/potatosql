CREATE TABLE ${table.name}
(
    <#if table.containsPrimaryKeys()>   
        <#list table.getIterator() as column>
        ${column.name} ${java_data_type.get_mariadb_data_type(column.typename)},
        </#list>        
        PRIMARY KEY (<#list table.getIterator()?filter(p -> p.isPrimaryKey()) as column>${column.name}<#if column?has_next>,</#if></#list>)
    <#else>
        <#list table.getIterator() as column>
        ${column.name} ${java_data_type.get_mariadb_data_type(column.typename)}<#if column?has_next>,</#if>
        </#list>
    </#if>
);
/
