@Override
public List<O_${table.name}> find_all(O_${table.name} ${table.name}) throws SQLException {
    BeanListHandler<O_${table.name}> beanListHandler = new BeanListHandler<>(O_${table.name}.class);
    ResultSet rs;
    try (CallableStatement proc = connection.prepareCall("{CALL ${table.schema_name}.${table.name}_select_all()}")) {
        rs = proc.executeQuery();
    }
    return beanListHandler.handle(rs);
}

