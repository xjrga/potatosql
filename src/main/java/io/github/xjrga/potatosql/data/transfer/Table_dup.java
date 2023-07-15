package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Table;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.hsqldb.types.Types;

public class Table_dup {
    private final Connection connection = Connect.getInstance().getConnection();
    public Integer duplicate( Table table ) {
        Integer id = null;
        try ( CallableStatement proc = connection.prepareCall( "{CALL potatosql.copy_table(?,?,?)}" ) ) {
            proc.registerOutParameter( 1, Types.INTEGER );
            proc.setInt( 2, table.getSchema_id() );
            proc.setInt( 3, table.getTable_id() );
            proc.execute();
            id = proc.getInt( 1 );
        } catch ( SQLException ex ) {
            System.out.println( ex.getMessage() );
        }
        return id;
    }
}
