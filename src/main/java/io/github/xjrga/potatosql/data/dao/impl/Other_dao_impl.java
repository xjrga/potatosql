package io.github.xjrga.potatosql.data.dao.impl;

import io.github.xjrga.potatosql.data.dto.O_key_pair_multiple_select;
import io.github.xjrga.potatosql.data.dto.O_key_with_name;
import io.github.xjrga.potatosql.data.dto.O_relationship;
import io.github.xjrga.potatosql.data.dto.O_relationship_multiple_select;
import io.github.xjrga.potatosql.data.dto.O_schema;
import io.github.xjrga.potatosql.data.dto.O_select_only_names;
import io.github.xjrga.potatosql.data.dto.O_table;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class Other_dao_impl {

    private final Connection connection;

    public Other_dao_impl( Connection connection ) {
        this.connection = connection;
    }

    public List<O_relationship_multiple_select> relationship_multiple_select( O_schema schema ) {
        List<O_relationship_multiple_select> list = null;
        BeanListHandler<O_relationship_multiple_select> beanListHandler = new BeanListHandler<>( O_relationship_multiple_select.class );
        try {
            CallableStatement proc = connection.prepareCall( "{call relationship_multiple_select(?)}" );
            proc.setInt( 1, schema.getSchema_id() );
            ResultSet rs = proc.executeQuery();
            proc.close();
            list = beanListHandler.handle( rs );
        } catch ( SQLException ex ) {
        }
        return list;
    }

    public List<O_select_only_names> relationship_select_only_names( O_schema schema ) {
        List<O_select_only_names> list = null;
        BeanListHandler<O_select_only_names> beanListHandler = new BeanListHandler<>( O_select_only_names.class );
        try {
            CallableStatement proc = connection.prepareCall( "{call relationship_select_only_names(?)}" );
            proc.setInt( 1, schema.getSchema_id() );
            ResultSet rs = proc.executeQuery();
            proc.close();
            list = beanListHandler.handle( rs );
        } catch ( SQLException ex ) {
        }
        return list;
    }

    public List<O_key_pair_multiple_select> relationship_key_pair_multiple_select( O_relationship relationship ) {
        List<O_key_pair_multiple_select> list = null;
        BeanListHandler<O_key_pair_multiple_select> beanListHandler = new BeanListHandler<>( O_key_pair_multiple_select.class );
        try {
            CallableStatement proc = connection.prepareCall( "{call relationship_key_pair_multiple_select(?,?,?,?)}" );
            proc.setInt( 1, relationship.getSchema_id() );
            proc.setInt( 2, relationship.getParent_table_id() );
            proc.setInt( 3, relationship.getChild_table_id() );
            proc.setInt( 4, relationship.getRelationship_id() );
            ResultSet rs = proc.executeQuery();
            proc.close();
            list = beanListHandler.handle( rs );
        } catch ( SQLException ex ) {
        }
        return list;
    }

    public void table_copy( O_table table_old, O_table table_new ) {
        try {
            CallableStatement proc = connection.prepareCall( "{call table_copy(?,?,?,?)}" );
            proc.setInt( 1, table_old.getSchema_id() );
            proc.setInt( 2, table_old.getTable_id() );
            proc.setInt( 3, table_new.getTable_id() );
            proc.setString( 4, table_new.getTable_name() );
            proc.execute();
            proc.close();
        } catch ( SQLException ex ) {
            ex.printStackTrace();
        }
    }

    public void schema_copy( O_schema schema_old, O_schema schema_new ) {
        try {
            CallableStatement proc = connection.prepareCall( "{call schema_copy(?,?,?)}" );
            proc.setInt( 1, schema_old.getSchema_id() );
            proc.setInt( 2, schema_new.getSchema_id() );
            proc.setString( 3, schema_new.getSchema_name() );
            proc.execute();
            proc.close();
        } catch ( SQLException ex ) {
            ex.printStackTrace();
        }
    }

    public List<O_key_with_name> table_key_select_with_name( O_key_with_name key ) {
        List<O_key_with_name> list = null;
        BeanListHandler<O_key_with_name> beanListHandler = new BeanListHandler<>( O_key_with_name.class );
        try {
            CallableStatement proc = connection.prepareCall( "{call table_key_select_with_name(?,?)}" );
            proc.setInt( 1, key.getSchema_id() );
            proc.setInt( 2, key.getTable_id() );
            ResultSet rs = proc.executeQuery();
            proc.close();
            list = beanListHandler.handle( rs );
        } catch ( SQLException ex ) {
        }
        return list;
    }

    public Integer generate_id() {
        Integer id = -1;
        try {
            CallableStatement proc = connection.prepareCall( "{call generate_id()}" );
            ResultSet rs = proc.executeQuery();
            proc.close();
            rs.next();
            id = rs.getInt( 1 );
        } catch ( SQLException ex ) {
        }
        return id;
    }

    public String export_schema( O_schema schema ) {
        String doc = "";
        try {
            CallableStatement proc = connection.prepareCall( "{CALL export_xml( ? )}" );
            proc.setInt( 1, schema.getSchema_id() );
            ResultSet rs = proc.executeQuery();
            while ( rs.next() ) {
                doc = rs.getString( 1 );
            }
            proc.close();
        } catch ( SQLException ex ) {
        }
        return doc;
    }

    public Boolean is_it_a_dependent_table( Integer schema_id, Integer table_id ) {
        Boolean flag = false;
        try {
            CallableStatement proc = connection.prepareCall( "{CALL is_it_a_dependent_table( ?, ? )}" );
            proc.setInt( 1, schema_id );
            proc.setInt( 2, table_id );
            ResultSet rs = proc.executeQuery();
            proc.close();
            rs.next();
            flag = rs.getBoolean( 1 );
        } catch ( SQLException ex ) {
        }
        return flag;
    }

}
