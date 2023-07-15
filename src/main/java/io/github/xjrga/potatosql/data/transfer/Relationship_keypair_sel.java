package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Keypair_mod;
import io.github.xjrga.potatosql.data.object.Relationship;
import io.github.xjrga.potatosql.functions.R1;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class Relationship_keypair_sel
        implements R1<Relationship, List<Keypair_mod>> {
    private final Connection connection = Connect.getInstance().getConnection();
    @Override
    public List<Keypair_mod> apply( Relationship relationship ) {
        List<Keypair_mod> list = null;
        BeanListHandler<Keypair_mod> beanListHandler = new BeanListHandler<>( Keypair_mod.class );
        try ( CallableStatement proc = connection.prepareCall( "{CALL potatosql.Relationship_key_pair_select(?,?)}" ); ) {
            proc.setInt( 1, relationship.getSchema_id() );
            proc.setInt( 2, relationship.getRelationship_id() );
            ResultSet rs = proc.executeQuery();
            list = beanListHandler.handle( rs );
        } catch ( SQLException ex ) {
        }
        return list;
    }
}
