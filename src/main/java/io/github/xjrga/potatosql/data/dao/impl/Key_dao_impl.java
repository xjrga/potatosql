package io.github.xjrga.potatosql.data.dao.impl;

import io.github.xjrga.potatosql.data.dao.Key_dao;
import io.github.xjrga.potatosql.data.dto.O_key;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class Key_dao_impl implements Key_dao {

    private final Connection connection;

    public Key_dao_impl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(O_key key) {
        try {
            CallableStatement proc = connection.prepareCall("{call table_key_insert(?,?,?,?,?,?,?,?)}");
            proc.setInt(1, key.getSchema_id());
            proc.setInt(2, key.getTable_id());
            proc.setInt(3, key.getTable_key_id());
            proc.setString(4, key.getTable_key_name());
            proc.setString(5, key.getTable_key_label());
            proc.setBoolean(6, key.getTable_key_is_pk());
            proc.setInt(7, key.getTable_key_type_id());
            proc.setInt(8, key.getTable_key_order());
            proc.execute();
            proc.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<O_key> find(O_key key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<O_key> find_all_key(O_key key) {
        List<O_key> list = new ArrayList<>();
        CallableStatement proc = null;
        BeanListHandler<O_key> beanListHandler = new BeanListHandler<>(O_key.class);
        try {
            proc = connection.prepareCall("{call table_key_select(?,?)}");
            proc.setInt(1, key.getSchema_id());
            proc.setInt(2, key.getTable_id());
            ResultSet rs = proc.executeQuery();
            proc.close();
            list = beanListHandler.handle(rs);
        } catch (SQLException ex) {
        }
        return list;
    }

    public List<O_key> find_key(O_key key) {
        List<O_key> list = new ArrayList<>();
        CallableStatement proc = null;
        BeanListHandler<O_key> beanListHandler = new BeanListHandler<>(O_key.class);
        try {
            proc = connection.prepareCall("{call table_key_select(?,?,?)}");
            proc.setInt(1, key.getSchema_id());
            proc.setInt(2, key.getTable_id());
            proc.setInt(3, key.getTable_key_id());
            ResultSet rs = proc.executeQuery();
            proc.close();
            list = beanListHandler.handle(rs);
        } catch (SQLException ex) {
        }
        return list;
    }

    @Override
    public List<O_key> find_all(O_key key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<O_key> find_pk(O_key key) {
        List<O_key> list = new ArrayList<>();
        CallableStatement proc = null;
        BeanListHandler<O_key> beanListHandler = new BeanListHandler<>(O_key.class);
        try {
            proc = connection.prepareCall("{call table_key_select_pk(?,?)}");
            proc.setInt(1, key.getSchema_id());
            proc.setInt(2, key.getTable_id());
            ResultSet rs = proc.executeQuery();
            proc.close();
            list = beanListHandler.handle(rs);
        } catch (SQLException ex) {
        }
        return list;
    }

    public List<O_key> find_npk(O_key key) {
        List<O_key> list = new ArrayList<>();
        CallableStatement proc = null;
        BeanListHandler<O_key> beanListHandler = new BeanListHandler<>(O_key.class);
        try {
            proc = connection.prepareCall("{call table_key_select_npk(?,?)}");
            proc.setInt(1, key.getSchema_id());
            proc.setInt(2, key.getTable_id());
            ResultSet rs = proc.executeQuery();
            proc.close();
            list = beanListHandler.handle(rs);
        } catch (SQLException ex) {
        }
        return list;
    }

    @Override
    public void update(O_key key) {
        try {
            CallableStatement proc = connection.prepareCall("{call table_key_update(?,?,?,?,?,?,?,?)}");
            proc.setInt(1, key.getSchema_id());
            proc.setInt(2, key.getTable_id());
            proc.setInt(3, key.getTable_key_id());
            proc.setString(4, key.getTable_key_name());
            proc.setString(5, key.getTable_key_label());
            proc.setBoolean(6, key.getTable_key_is_pk());
            proc.setInt(7, key.getTable_key_type_id());
            proc.setInt(8, key.getTable_key_order());
            proc.execute();
            proc.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(O_key key) {
        try {
            CallableStatement proc = connection.prepareCall("{call table_key_delete(?,?,?)}");
            proc.setInt(1, key.getSchema_id());
            proc.setInt(2, key.getTable_id());
            proc.setInt(3, key.getTable_key_id());
            proc.execute();
            proc.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
