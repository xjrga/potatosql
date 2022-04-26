package io.github.xjrga.potatosql.data.dao.impl;

import io.github.xjrga.potatosql.data.dao.Schema_dao;
import io.github.xjrga.potatosql.data.dto.O_schema;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class Schema_dao_impl implements Schema_dao {

    private final Connection connection;

    public Schema_dao_impl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(O_schema schema) {
        try {
            CallableStatement proc = connection.prepareCall("{call database_schema_insert(?,?)}");
            proc.setInt(1, schema.getSchema_id());
            proc.setString(2, schema.getSchema_name());
            proc.execute();
            proc.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<O_schema> find(O_schema schema) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<O_schema> find_all(O_schema schema) {
        List<O_schema> list = null;
        BeanListHandler<O_schema> beanListHandler = new BeanListHandler<>(O_schema.class);
        try {
            CallableStatement proc = connection.prepareCall("{call database_schema_select_all()}");
            ResultSet rs = proc.executeQuery();
            proc.close();
            list = beanListHandler.handle(rs);
        } catch (SQLException ex) {
        }
        return list;
    }

    @Override
    public void update(O_schema schema) {
        try {
            CallableStatement proc = connection.prepareCall("{call database_schema_update(?,?)}");
            proc.setInt(1, schema.getSchema_id());
            proc.setString(2, schema.getSchema_name());
            proc.execute();
            proc.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(O_schema schema) {
        try {
            CallableStatement proc = connection.prepareCall("{call database_schema_delete(?)}");
            proc.setInt(1, schema.getSchema_id());
            proc.execute();
            proc.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
