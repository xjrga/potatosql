package io.github.xjrga.potatosql.data.dao.implementation;

import io.github.xjrga.potatosql.data.dao.impl.Schema_dao_impl;
import io.github.xjrga.potatosql.data.dao.impl.Table_dao_impl;
import io.github.xjrga.potatosql.data.dao.impl.Key_dao_impl;
import io.github.xjrga.potatosql.data.dao.impl.Other_dao_impl;
import io.github.xjrga.potatosql.data.Connections;
import io.github.xjrga.potatosql.data.Dbactions;
import io.github.xjrga.potatosql.data.dto.O_key;
import io.github.xjrga.potatosql.data.dto.O_key_with_name;
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
public class Key_dao_implTest {

    private static Connection connection;
    private Schema_dao_impl schema_dao;
    private Table_dao_impl table_dao;
    private Key_dao_impl key_dao;
    private Other_dao_impl other_dao;
    private final int schema_id = 1;
    private final int table_id = 0;
    private final int key_id = 0;

    public Key_dao_implTest() {

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
        key_dao = new Key_dao_impl(connection);
        other_dao = new Other_dao_impl(connection);
    }

    @After
    public void tearDown() {
    }

    @Ignore
    public void test() {
        test_insert_schema();
        test_insert_table();
        test_insert_key();
        test_keys_with_name();
        test_update_key();
        test_delete_key();
        test_insert_npk_key();
        test_delete_key();
        test_insert_pk_key();
        test_delete_key();
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
        List<O_table> actual = table_dao.find_all(table);
        check_tables(expected, actual);
    }

    public void test_insert_key() {
        System.out.println("Test insert key");
        List<O_key> expected_keys = new ArrayList<>();
        O_key table_key = new O_key();
        table_key.setSchema_id(schema_id);
        table_key.setTable_id(table_id);
        table_key.setTable_key_id(key_id);
        table_key.setTable_key_name("Test key name");
        table_key.setTable_key_label("Test key label");
        table_key.setTable_key_is_pk(false);
        table_key.setTable_key_type_id(2);
        table_key.setTable_key_order(0);
        key_dao.insert(table_key);
        List<O_key> actual_keys = key_dao.find_key(table_key);
        expected_keys.add(table_key);
        check_keys(expected_keys, actual_keys);
    }

    public void test_update_key() {
        System.out.println("Test update key");
        List<O_key> expected_keys = new ArrayList<>();
        O_key table_key = new O_key();
        table_key.setSchema_id(schema_id);
        table_key.setTable_id(table_id);
        table_key.setTable_key_id(key_id);
        table_key.setTable_key_name("Update - Test key name");
        table_key.setTable_key_label("Update - Test key label");
        table_key.setTable_key_is_pk(false);
        table_key.setTable_key_type_id(2);
        table_key.setTable_key_order(0);
        expected_keys.add(table_key);
        key_dao.update(table_key);
        List<O_key> actual_keys = key_dao.find_key(table_key);
        check_keys(expected_keys, actual_keys);
    }

    public void test_delete_key() {
        System.out.println("Test delete schema");
        List<O_key> expected_keys = new ArrayList<>();
        O_key table_key = new O_key();
        table_key.setSchema_id(schema_id);
        table_key.setTable_id(table_id);
        table_key.setTable_key_id(key_id);
        key_dao.delete(table_key);
        List<O_key> actual_keys = key_dao.find_key(table_key);
        check_keys(expected_keys, actual_keys);
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

    public void test_insert_npk_key() {
        System.out.println("Test insert npk key");
        List<O_key> expected_keys = new ArrayList<>();
        O_key table_key = new O_key();
        table_key.setSchema_id(schema_id);
        table_key.setTable_id(table_id);
        table_key.setTable_key_id(key_id);
        table_key.setTable_key_name("Test key name");
        table_key.setTable_key_label("Test key label");
        table_key.setTable_key_is_pk(false);
        table_key.setTable_key_type_id(2);
        table_key.setTable_key_order(0);
        expected_keys.add(table_key);
        key_dao.insert(table_key);
        List<O_key> actual_keys = key_dao.find_npk(table_key);
        check_keys(expected_keys, actual_keys);
        //actual_keys = key_dao.find_pk(table_key);
        //check_keys(expected_keys, actual_keys);
    }

    public void test_insert_pk_key() {
        System.out.println("Test insert pk key");
        List<O_key> expected_keys = new ArrayList<>();
        O_key table_key = new O_key();
        table_key.setSchema_id(schema_id);
        table_key.setTable_id(table_id);
        table_key.setTable_key_id(key_id);
        table_key.setTable_key_name("Test key name");
        table_key.setTable_key_label("Test key label");
        table_key.setTable_key_is_pk(true);
        table_key.setTable_key_type_id(2);
        table_key.setTable_key_order(0);
        expected_keys.add(table_key);
        key_dao.insert(table_key);
        List<O_key> actual_keys = key_dao.find_pk(table_key);
        check_keys(expected_keys, actual_keys);
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

    private void check_keys(List<O_key> expResult, List<O_key> result) {
        assertEquals(expResult.size(), result.size());
        for (int i = 0; i < expResult.size(); i++) {
            assertEquals(expResult.get(i).getSchema_id(), result.get(i).getSchema_id());
            assertEquals(expResult.get(i).getTable_id(), result.get(i).getTable_id());
            assertEquals(expResult.get(i).getTable_key_id(), result.get(i).getTable_key_id());
            assertEquals(expResult.get(i).getTable_key_name(), result.get(i).getTable_key_name());
            assertEquals(expResult.get(i).getTable_key_label(), result.get(i).getTable_key_label());
            assertEquals(expResult.get(i).getTable_key_is_pk(), result.get(i).getTable_key_is_pk());
            assertEquals(expResult.get(i).getTable_key_type_id(), result.get(i).getTable_key_type_id());
            assertEquals(expResult.get(i).getTable_key_order(), result.get(i).getTable_key_order());
        }
    }

    private void check_keys_with_name(List<O_key_with_name> expected, List<O_key_with_name> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getSchema_id(), actual.get(i).getSchema_id());
            assertEquals(expected.get(i).getTable_id(), actual.get(i).getTable_id());
            assertEquals(expected.get(i).getTable_key_id(), actual.get(i).getTable_key_id());
            assertEquals(expected.get(i).getTable_key_name(), actual.get(i).getTable_key_name());
            assertEquals(expected.get(i).getTable_key_label(), actual.get(i).getTable_key_label());
            assertEquals(expected.get(i).getTable_key_is_pk(), actual.get(i).getTable_key_is_pk());
            assertEquals(expected.get(i).getTable_key_type_id(), actual.get(i).getTable_key_type_id());
            assertEquals(expected.get(i).getKey_type_name(), actual.get(i).getKey_type_name());
            assertEquals(expected.get(i).getTable_key_order(), actual.get(i).getTable_key_order());
        }
    }

    public void test_keys_with_name() {
        System.out.println("Test keys with name");
        List<O_key_with_name> expected_keys = new ArrayList<>();
        O_key_with_name table_key = new O_key_with_name();
        table_key.setSchema_id(schema_id);
        table_key.setTable_id(table_id);
        table_key.setTable_key_id(key_id);
        table_key.setTable_key_name("Test key name");
        table_key.setTable_key_label("Test key label");
        table_key.setTable_key_is_pk(false);
        table_key.setTable_key_type_id(2);
        table_key.setKey_type_name("LONGVARCHAR");
        table_key.setTable_key_order(0);
        expected_keys.add(table_key);
        List<O_key_with_name> actual_keys = other_dao.table_key_select_with_name(table_key);
        check_keys_with_name(expected_keys, actual_keys);
    }

}
