package io.github.xjrga.potatosql.data.dao.impl;

import io.github.xjrga.potatosql.data.dao.Relationship_dao;
import io.github.xjrga.potatosql.data.dto.O_relationship;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class Relationship_dao_impl implements Relationship_dao {

    private final Connection connection;

    public Relationship_dao_impl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(O_relationship relationship) {
        try {
            CallableStatement proc = connection.prepareCall("{call relationship_insert(?,?,?,?,?)}");
            proc.setInt(1, relationship.getSchema_id());
            proc.setInt(2, relationship.getParent_table_id());
            proc.setInt(3, relationship.getChild_table_id());
            proc.setInt(4, relationship.getRelationship_id());
            proc.setInt(5, relationship.getRelationship_type_id());
            proc.execute();
            proc.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<O_relationship> find(O_relationship o) {
        List<O_relationship> list = null;
        CallableStatement proc = null;
        BeanListHandler<O_relationship> beanListHandler = new BeanListHandler<>(O_relationship.class);
        try {
            proc = connection.prepareCall("{call relationship_select(?)}");
            proc.setInt(1, o.getSchema_id());
            ResultSet rs = proc.executeQuery();
            proc.close();
            list = beanListHandler.handle(rs);
        } catch (SQLException ex) {

        }
        return list;
    }

    @Override
    public List<O_relationship> find_all(O_relationship o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(O_relationship relationship) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(O_relationship relationship) {
        try {
            CallableStatement proc = connection.prepareCall("{call relationship_delete(?,?,?,?)}");
            proc.setInt(1, relationship.getSchema_id());
            proc.setInt(2, relationship.getParent_table_id());
            proc.setInt(3, relationship.getChild_table_id());
            proc.setInt(4, relationship.getRelationship_id());
            proc.execute();
            proc.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
