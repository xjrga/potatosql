public void delete_all(O_${table.name} ${table.name}) throws SQLException {
        try (CallableStatement proc = connection.prepareCall("{CALL ${table.schema_name}.${table.name}_delete_all()}")) {
            proc.execute();
        }
    }

