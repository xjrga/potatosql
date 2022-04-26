package io.github.xjrga.potatosql.data.dao.implementation;

import io.github.xjrga.potatosql.data.dao.impl.Other_dao_impl;
import io.github.xjrga.potatosql.data.Connections;
import io.github.xjrga.potatosql.data.Dbactions;
import io.github.xjrga.potatosql.data.dto.O_schema;
import io.github.xjrga.potatosql.data.dto.O_table;
import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

/**
 *
 * @author jr
 */
public class Other_dao_implTest {

    private static Connection connection;
    private Other_dao_impl other_dao;

    public Other_dao_implTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        connection = Connections.TESTING.get_connection();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        Dbactions.shutdown(connection);
    }

    @Before
    public void setUp() throws Exception {
        other_dao = new Other_dao_impl(connection);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Ignore
    public void test() {

    }

    @Ignore
    public void test_table_copy() {
        System.out.println("Table copy");
        O_table older = new O_table();
        older.setSchema_id(0);
        older.setTable_id(0);
        O_table newer = new O_table();
        newer.setTable_id(10);
        newer.setTable_name("New Person");
        other_dao.table_copy(older, newer);
    }

    @Ignore
    public void test_schema_copy() {
        System.out.println("Schema copy");
        O_schema older = new O_schema();
        older.setSchema_id(0);
        O_schema newer = new O_schema();
        newer.setSchema_id(1);
        newer.setSchema_name("New rolodex");
        other_dao.schema_copy(older, newer);
    }
}
