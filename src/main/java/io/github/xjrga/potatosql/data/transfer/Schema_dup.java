package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Schema;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class Schema_dup {
    private final Connection connection = Connect.getInstance().getConnection();
    public Integer duplicate( Schema schema ) {
        Integer id = null;
        try ( CallableStatement proc = connection.prepareCall( "{CALL potatosql.copy_schema(?,?)}" ) ) {
            proc.registerOutParameter( 1, Types.INTEGER );
            proc.setInt( 2, schema.getSchema_id() );
            proc.execute();
            id = proc.getInt( 1 );
        } catch ( SQLException ex ) {
            System.out.println( ex.getMessage() );
        }
        return id;
    }
}
