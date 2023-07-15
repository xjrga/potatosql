package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Key;
import io.github.xjrga.potatosql.data.object.Relationship;
import io.github.xjrga.potatosql.functions.R1;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class Table_child_key_sel
        implements R1<Relationship, List<Key>> {
    private final Connection connection = Connect.getInstance().getConnection();
    @Override
    public List<Key> apply( Relationship relationship ) {
        List<Key> list = null;
        BeanListHandler<Key> beanListHandler = new BeanListHandler<>( Key.class );
        try ( CallableStatement proc = connection.prepareCall( "{CALL potatosql.Table_child_key_select(?,?,?)}" ); ) {
            proc.setInt( 1, relationship.getSchema_id() );
            proc.setInt( 2, relationship.getTable_id_child() );
            proc.setBoolean( 3, relationship.getIs_identifying() );
            ResultSet rs = proc.executeQuery();
            list = beanListHandler.handle( rs );
        } catch ( SQLException ex ) {
        }
        return list;
    }
}
