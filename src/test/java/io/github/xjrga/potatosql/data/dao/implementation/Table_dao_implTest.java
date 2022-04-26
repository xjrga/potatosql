package io.github.xjrga.potatosql.data.dao.implementation;

import io.github.xjrga.potatosql.data.dao.impl.Schema_dao_impl;
import io.github.xjrga.potatosql.data.dao.impl.Table_dao_impl;
import io.github.xjrga.potatosql.data.Connections;
import io.github.xjrga.potatosql.data.Dbactions;
import io.github.xjrga.potatosql.data.dto.O_schema;
import io.github.xjrga.potatosql.data.dto.O_table;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

/**
 *
 * @author jr
 */
public class Table_dao_implTest {

    private static Connection connection;
    private Schema_dao_impl schema_dao;
    private Table_dao_impl table_dao;
    private final int schema_id = 1;
    private final int table_id = 0;

    public Table_dao_implTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        connection = Connections.TESTING.get_connection();
    }

    @AfterClass
    public static void tearDownClass() {
        Dbactions.shutdown(connection);
    }

    @Before
    public void setUp() {
        schema_dao = new Schema_dao_impl(connection);
        table_dao = new Table_dao_impl(connection);
    }

    @After
    public void tearDown() {
    }

    @Ignore
    public void test() {
        test_insert_schema();
        test_insert_table();
        test_update_table();
        test_delete_table();
        test_delete_schema();
    }

    public void test_insert_schema() {
        System.out.println("Test insert schema");
        List<O_schema> expected = new ArrayList<>();
        O_schema schema00 = new O_schema();
        schema00.setSchema_id(schema_id);
        schema00.setSchema_name("Rolodex");
        expected.add(schema00);
        schema_dao.insert(schema00);
        List<O_schema> actual = schema_dao.find_all(new O_schema());
        check_schema(expected, actual);
    }

    public void test_insert_table() {
        System.out.println("Test insert table");
        List<O_table> expected = new ArrayList<>();
        O_table table = new O_table();
        table.setSchema_id(schema_id);
        table.setTable_id(table_id);
        table.setTable_name("Test");
        expected.add(table);
        table_dao.insert(table);
        List<O_table> actual = table_dao.find_02(table);
        check_tables(expected, actual);
    }

    public void test_update_table() {
        System.out.println("Test update table");
        List<O_table> expected = new ArrayList<>();
        O_table table = new O_table();
        table.setSchema_id(schema_id);
        table.setTable_id(table_id);
        table.setTable_name("Updated table");
        expected.add(table);
        table_dao.update(table);
        List<O_table> actual = table_dao.find_02(table);
        check_tables(expected, actual);
    }

    public void test_delete_table() {
        System.out.println("Test delete table");
        List<O_table> expected = new ArrayList<>();
        O_table table = new O_table();
        table.setSchema_id(schema_id);
        table.setTable_id(table_id);
        table_dao.delete(table);
        List<O_table> actual = table_dao.find_02(table);
        check_tables(expected, actual);
    }

    public void test_delete_schema() {
        System.out.println("Test delete schema");
        List<O_schema> expected = new ArrayList<>();
        O_schema schema00 = new O_schema();
        schema00.setSchema_id(schema_id);
        schema_dao.delete(schema00);
        List<O_schema> actual = schema_dao.find_all(new O_schema());
        check_schema(expected, actual);
    }

    private void check_schema(List<O_schema> expected, List<O_schema> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i).getSchema_id(), actual.get(i).getSchema_id());
            assertEquals(expected.get(i).getSchema_name(), actual.get(i).getSchema_name());
        }
    }

    private void check_tables(List<O_table> expResult, List<O_table> result) {
        assertEquals(expResult.size(), result.size());
        for (int i = 0; i < expResult.size(); i++) {
            assertEquals(expResult.get(i).getSchema_id(), result.get(i).getSchema_id());
            assertEquals(expResult.get(i).getTable_id(), result.get(i).getTable_id());
            assertEquals(expResult.get(i).getTable_name(), result.get(i).getTable_name());
        }
    }

}
