package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Relationship_mod;
import io.github.xjrga.potatosql.data.object.Schema;
import io.github.xjrga.potatosql.functions.R1;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class Relationship_sel
        implements R1<Schema, List<Relationship_mod>> {
    private final Connection connection = Connect.getInstance().getConnection();
    @Override
    public List<Relationship_mod> apply( Schema schema ) {
        List<Relationship_mod> list = null;
        BeanListHandler<Relationship_mod> beanListHandler = new BeanListHandler<>( Relationship_mod.class );
        try ( CallableStatement proc = connection.prepareCall( "{CALL potatosql.Relationship_select(?)}" ); ) {
            proc.setInt( 1, schema.getSchema_id() );
            ResultSet rs = proc.executeQuery();
            list = beanListHandler.handle( rs );
        } catch ( SQLException ex ) {
        }
        return list;
    }
}
