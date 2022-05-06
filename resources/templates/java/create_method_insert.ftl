@Override
public void insert(O_${table.name} ${table.name}) throws SQLException {
    try (CallableStatement proc = connection.prepareCall("{CALL ${table.schema_name}.${table.name}_insert(<#list table.getIterator() as column>?<#if column?has_next>,</#if></#list>)}")) {
        <#list table.getIterator() as column>
        proc.set${util.capitalize(java_data_type.get_method_data_type(column.typename))}(${column?counter}, ${table.name}.get${util.capitalize(column.name)}());
        </#list>
        proc.execute();
    }
}

