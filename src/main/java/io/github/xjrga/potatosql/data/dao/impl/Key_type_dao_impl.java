package io.github.xjrga.potatosql.data.dao.impl;

import io.github.xjrga.potatosql.data.dao.Key_type_dao;
import io.github.xjrga.potatosql.data.dto.O_key_type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class Key_type_dao_impl implements Key_type_dao {

    private final Connection connection;

    public Key_type_dao_impl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(O_key_type key_type) {

    }

    @Override
    public List<O_key_type> find(O_key_type key_type) {
        return null;
    }

    @Override
    public List<O_key_type> find_all(O_key_type key_type) {
        List<O_key_type> list = new ArrayList<>();
        BeanListHandler<O_key_type> beanListHandler = new BeanListHandler<>(O_key_type.class);
        try {
            CallableStatement proc = connection.prepareCall("{call key_type_select_all()}");
            ResultSet rs = proc.executeQuery();
            proc.close();
            list = beanListHandler.handle(rs);
        } catch (SQLException ex) {
        }
        return list;
    }

    @Override
    public void update(O_key_type key_type) {

    }

    @Override
    public void delete(O_key_type key_type) {

    }

}
