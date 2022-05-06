@Override
public Integer count(O_${table.name} ${table.name}) throws SQLException {
    Integer out;
    try (CallableStatement proc = connection.prepareCall("{? = CALL ${table.schema_name}.${table.name}_count()}")) {
        proc.registerOutParameter(1, Types.INTEGER);
        proc.execute();
        out = proc.getInt(1);
    }
    return out;
}

