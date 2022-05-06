@Override
public List<O_${table.name}> find(O_${table.name} ${table.name}) throws SQLException {
    BeanListHandler<O_${table.name}> beanListHandler = new BeanListHandler<>(O_${table.name}.class);
    ResultSet rs;
    try (CallableStatement proc = connection.prepareCall("{CALL ${table.schema_name}.${table.name}_select(<#list table.getIterator()?filter(p -> p.isPrimaryKey()) as column>?<#if column?has_next>,</#if></#list>)}")) {
        <#list table.getIterator()?filter(p -> p.isPrimaryKey()) as column>
        proc.set${util.capitalize(java_data_type.get_method_data_type(column.typename))}(${column?counter}, ${table.name}.get${util.capitalize(column.name)}());
        </#list>   
        rs = proc.executeQuery();
    }
    return beanListHandler.handle(rs);
}

