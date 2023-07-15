package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Keypair;
import io.github.xjrga.potatosql.functions.V1;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Identifying_relationship_keypair_ins
        implements V1<Keypair> {
    private final Connection connection = Connect.getInstance().getConnection();
    @Override
    public void apply( Keypair pair ) {
        try ( CallableStatement proc = connection.prepareCall( "{CALL potatosql.Identifying_relationship_key_pair_insert(?,?,?,?,?,?)}" ) ) {
            proc.setInt( 1, pair.getSchema_id() );
            proc.setInt( 2, pair.getRelationship_id() );
            proc.setInt( 3, pair.getTable_id_parent() );
            proc.setInt( 4, pair.getKey_id_parent() );
            proc.setInt( 5, pair.getTable_id_child() );
            proc.setInt( 6, pair.getKey_id_child() );
            proc.execute();
        } catch ( SQLException ex ) {
            System.out.println( ex.getMessage() );
        }
    }
}
