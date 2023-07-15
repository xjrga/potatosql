package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Schema;
import io.github.xjrga.potatosql.functions.R1;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class Schema_ins_auto
        implements R1<Schema, Integer> {
    private final Connection connection = Connect.getInstance().getConnection();
    @Override
    public Integer apply( Schema schema ) {
        Integer id = null;
        try ( CallableStatement proc = connection.prepareCall( "{CALL potatosql.Database_schema_insert_auto(?,?)}" ) ) {
            proc.registerOutParameter( 1, Types.INTEGER );
            proc.setString( 2, schema.getSchema_name() );
            proc.execute();
            id = proc.getInt( 1 );
        } catch ( SQLException ex ) {
            System.out.println( ex.getMessage() );
        }
        return id;
    }
}
