package io.github.xjrga.potatosql.data.transfer;

import io.github.xjrga.potatosql.data.Connect;
import io.github.xjrga.potatosql.data.object.Schema;
import io.github.xjrga.potatosql.data.object.Template_key;
import io.github.xjrga.potatosql.data.object.Template_table;
import io.github.xjrga.potatosql.functions.R2;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Template_key_sel
        implements R2<Schema, Template_table, Template_table> {
    private final Connection connection = Connect.getInstance().getConnection();
    @Override
    public Template_table apply( Schema schema, Template_table table_in ) {
        Template_table table_out = new Template_table();
        table_out.setTable_id( table_in.getTable_id() );
        table_out.setTable_name( table_in.getTable_name() );
        table_out.setIs_dependent( table_in.getIs_dependent() );
        try ( CallableStatement proc = connection.prepareCall( "{CALL potatosql.find_table_keys(?,?)}" ); ) {
            proc.setInt( 1, schema.getSchema_id() );
            proc.setInt( 2, table_in.getTable_id() );
            ResultSet rs = proc.executeQuery();
            while ( rs.next() ) {
                String key_name = rs.getString( 1 );
                boolean is_primary_key = rs.getBoolean( 2 );
                String datatype = rs.getString( 3 );
                int key_order = rs.getInt( 4 );
                boolean is_foreign_key = rs.getBoolean( 5 );
                table_out.add_key( new Template_key( key_name, is_primary_key, datatype, key_order, is_foreign_key ) );
            }
        } catch ( SQLException ex ) {
        }
        return table_out;
    }
}
