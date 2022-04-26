package io.github.xjrga.potatosql.data.dao.implementation;

import io.github.xjrga.potatosql.data.dao.impl.Schema_dao_impl;
import io.github.xjrga.potatosql.data.Connections;
import io.github.xjrga.potatosql.data.Dbactions;
import io.github.xjrga.potatosql.data.dto.O_schema;
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
public class Schema_dao_implTest {

    private static Connection connection;
    private Schema_dao_impl schema_dao;
    private final int schema_id_a = 1;
    private final int schema_id_b = 2;

    public Schema_dao_implTest() {

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
        schema_dao = new Schema_dao_impl(connection);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Ignore
    public void test() {
        test_insert_schema();
        test_update_schema();
        test_delete_schema();
    }

    public void test_insert_schema() {
        System.out.println("Test insert schema");
        List<O_schema> expected = new ArrayList<>();
        O_schema schema00 = new O_schema();
        schema00.setSchema_id(schema_id_a);
        schema00.setSchema_name("Rolodex");
        expected.add(schema00);
        schema_dao.insert(schema00);
        O_schema schema01 = new O_schema();
        schema01.setSchema_id(schema_id_b);
        schema01.setSchema_name("Sales");
        expected.add(schema01);
        schema_dao.insert(schema01);
        List<O_schema> actual = schema_dao.find_all(new O_schema());
        check_schema(expected, actual);
    }

    public void test_update_schema() {
        System.out.println("Test update schema");
        List<O_schema> expected = new ArrayList<>();
        O_schema schema00 = new O_schema();
        schema00.setSchema_id(schema_id_a);
        schema00.setSchema_name("Updated rolodex");
        expected.add(schema00);
        schema_dao.update(schema00);
        O_schema schema01 = new O_schema();
        schema01.setSchema_id(schema_id_b);
        schema01.setSchema_name("Sales");
        expected.add(schema01);
        List<O_schema> actual = schema_dao.find_all(new O_schema());
        check_schema(expected, actual);
    }

    public void test_delete_schema() {
        System.out.println("Test delete schema");
        List<O_schema> expected = new ArrayList<>();
        O_schema schema00 = new O_schema();
        schema00.setSchema_id(schema_id_a);
        schema_dao.delete(schema00);
        O_schema schema01 = new O_schema();
        schema01.setSchema_id(schema_id_b);
        schema_dao.delete(schema01);
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

}
