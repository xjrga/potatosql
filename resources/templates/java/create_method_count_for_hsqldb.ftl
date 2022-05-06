@Override
public Integer count(O_${table.name} ${table.name}) throws SQLException {
    Integer out;
    try ( CallableStatement proc = connection.prepareCall("{CALL ${table.schema_name}.${table.name}_count()}")) {
        proc.execute();
        ResultSet resultSet = proc.getResultSet();
        resultSet.next();
        out = resultSet.getInt(1);
    }
    return out;
}

