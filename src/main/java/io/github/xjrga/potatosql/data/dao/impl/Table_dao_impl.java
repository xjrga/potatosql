package io.github.xjrga.potatosql.data.dao.impl;

import io.github.xjrga.potatosql.data.dao.Table_dao;
import io.github.xjrga.potatosql.data.dto.O_table;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class Table_dao_impl implements Table_dao {

    private final Connection connection;

    public Table_dao_impl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(O_table table) {
        try {
            CallableStatement proc = connection.prepareCall("{call database_table_insert(?,?,?)}");
            proc.setInt(1, table.getSchema_id());
            proc.setInt(2, table.getTable_id());
            proc.setString(3, table.getTable_name());
            proc.execute();
            proc.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<O_table> find(O_table table) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<O_table> find_all(O_table table) {
        List<O_table> list = null;
        CallableStatement proc = null;
        BeanListHandler<O_table> beanListHandler = new BeanListHandler<>(O_table.class);
        try {
            proc = connection.prepareCall("{call database_table_select(?)}");
            proc.setInt(1, table.getSchema_id());
            ResultSet rs = proc.executeQuery();
            proc.close();
            list = beanListHandler.handle(rs);
        } catch (SQLException ex) {

        }
        return list;
    }

    public List<O_table> find_02(O_table table) {
        List<O_table> list = null;
        BeanListHandler<O_table> beanListHandler = new BeanListHandler<>(O_table.class);
        try {
            CallableStatement proc = connection.prepareCall("{call database_table_select(?,?)}");
            proc.setInt(1, table.getSchema_id());
            proc.setInt(2, table.getTable_id());
            ResultSet rs = proc.executeQuery();
            proc.close();
            list = beanListHandler.handle(rs);
        } catch (SQLException ex) {

        }
        return list;
    }

    @Override
    public void update(O_table table) {
        try {
            CallableStatement proc = connection.prepareCall("{call database_table_update(?,?,?)}");
            proc.setInt(1, table.getSchema_id());
            proc.setInt(2, table.getTable_id());
            proc.setString(3, table.getTable_name());
            proc.execute();
            proc.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(O_table table) {
        try {
            CallableStatement proc = connection.prepareCall("{call database_table_delete(?,?)}");
            proc.setInt(1, table.getSchema_id());
            proc.setInt(2, table.getTable_id());
            proc.execute();
            proc.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
