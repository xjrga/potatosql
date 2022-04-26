package io.github.xjrga.potatosql.data.dao.impl;

import io.github.xjrga.potatosql.data.dao.Relationship_key_pair_dao;
import io.github.xjrga.potatosql.data.dto.O_relationship_key_pair;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Relationship_key_pair_dao_impl implements Relationship_key_pair_dao {

    private final Connection connection;

    public Relationship_key_pair_dao_impl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(O_relationship_key_pair key_pair) {
        try {
            CallableStatement proc = connection.prepareCall("{call relationship_key_pair_insert(?,?,?,?,?,?)}");
            proc.setInt(1, key_pair.getSchema_id());
            proc.setInt(2, key_pair.getParent_table_id());
            proc.setInt(3, key_pair.getChild_table_id());
            proc.setInt(4, key_pair.getRelationship_id());
            proc.setInt(5, key_pair.getParent_key_id());
            proc.setInt(6, key_pair.getChild_key_id());
            proc.execute();
            proc.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<O_relationship_key_pair> find(O_relationship_key_pair key_pair) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<O_relationship_key_pair> find_all(O_relationship_key_pair key_pair) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(O_relationship_key_pair key_pair) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(O_relationship_key_pair key_pair) {
        try {
            CallableStatement proc = connection.prepareCall("{call relationship_key_pair_delete(?,?,?,?,?,?)}");
            proc.setInt(1, key_pair.getSchema_id());
            proc.setInt(2, key_pair.getParent_table_id());
            proc.setInt(3, key_pair.getChild_table_id());
            proc.setInt(4, key_pair.getRelationship_id());
            proc.setInt(5, key_pair.getParent_key_id());
            proc.setInt(6, key_pair.getChild_key_id());
            proc.execute();
            proc.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
