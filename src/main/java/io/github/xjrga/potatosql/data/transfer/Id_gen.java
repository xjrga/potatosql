package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.functions.R0;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Id_gen
        implements R0<Integer> {
    private final Connection connection = Connect.getInstance().getConnection();
    @Override
    public Integer apply() {
        Integer id = -1;
        try {
            try ( CallableStatement proc = connection.prepareCall( "{call potatosql.id()}" ) ) {
                ResultSet rs = proc.executeQuery();
                rs.next();
                id = rs.getInt( 1 );
            }
        } catch ( SQLException ex ) {
            System.out.println( ex );
        }
        return id;
    }
}
