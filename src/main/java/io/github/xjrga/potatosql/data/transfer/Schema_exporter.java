package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Schema;
import io.github.xjrga.potatosql.functions.R2;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Schema_exporter
        implements R2<Schema, String, String> {
    private final Connection connection = Connect.getInstance().getConnection();
    @Override
    public String apply( Schema schema, String path ) {
        String txt = null;
        try ( CallableStatement proc = connection.prepareCall( "{CALL potatosql.export_as_xml(?)}" ); ) {
            proc.setInt( 1, schema.getSchema_id() );
            ResultSet rs = proc.executeQuery();
            rs.next();
            txt = rs.getString( 1 );
        } catch ( SQLException ex ) {
        }
        return txt;
    }
}
