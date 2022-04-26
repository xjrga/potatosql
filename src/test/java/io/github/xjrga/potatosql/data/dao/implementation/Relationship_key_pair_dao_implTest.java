package io.github.xjrga.potatosql.data.dao.implementation;

import io.github.xjrga.potatosql.data.dao.impl.Relationship_key_pair_dao_impl;
import io.github.xjrga.potatosql.data.dao.impl.Schema_dao_impl;
import io.github.xjrga.potatosql.data.dao.impl.Table_dao_impl;
import io.github.xjrga.potatosql.data.dao.impl.Relationship_dao_impl;
import io.github.xjrga.potatosql.data.dao.impl.Key_dao_impl;
import io.github.xjrga.potatosql.data.dao.impl.Other_dao_impl;
import io.github.xjrga.potatosql.data.Connections;
import io.github.xjrga.potatosql.data.Dbactions;
import io.github.xjrga.potatosql.data.dto.O_key;
import io.github.xjrga.potatosql.data.dto.O_key_pair_multiple_select;
import io.github.xjrga.potatosql.data.dto.O_relationship;
import io.github.xjrga.potatosql.data.dto.O_relationship_key_pair;
import io.github.xjrga.potatosql.data.dto.O_schema;
import io.github.xjrga.potatosql.data.dto.O_select_only_names;
import io.github.xjrga.potatosql.data.dto.O_table;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jr
 */
public class Relationship_key_pair_dao_implTest {

    private static Connection connection;
    private Schema_dao_impl schema_dao;
    private Table_dao_impl table_dao;
    private Relationship_dao_impl relationship_dao;
    private Key_dao_impl key_dao;
    private Relationship_key_pair_dao_impl key_pair_dao;
    private Other_dao_impl other_dao;
    private final int schema_id = 1;
    private final int table_id_01 = 0;
    private final int table_id_02 = 1;

    public Relationship_key_pair_dao_implTest() {
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
        relationship_dao = new Relationship_dao_impl(connection);
        key_dao = new Key_dao_impl(connection);
        key_pair_dao = new Relationship_key_pair_dao_impl(connection);
        other_dao = new Other_dao_impl(connection);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test() {
        test_insert_schema();
        test_insert_table_01();
        test_insert_key_table_01();
        test_insert_table_02();
        test_insert_key_table_02();
        test_relationship_insert();
        test_relationship_key_pair_insert();
        test_relationship_select_only_names();
        test_relationship_key_pair_multiple_select();
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

    public void test_delete_schema() {
        System.out.println("Test delete schema");
        List<O_schema> expected = new ArrayList<>();
        O_schema schema00 = new O_schema();
        schema00.setSchema_id(schema_id);
        schema_dao.delete(schema00);
        List<O_schema> actual = schema_dao.find_all(new O_schema());
        check_schema(expected, actual);
    }

    public void test_insert_table_01() {
        System.out.println("Test insert table 01");
        List<O_table> expected = new ArrayList<>();
        O_table table = new O_table();
        table.setSchema_id(schema_id);
        table.setTable_id(table_id_01);
        table.setTable_name("Class");
        expected.add(table);
        table_dao.insert(table);
        List<O_table> actual = table_dao.find_02(table);
        check_tables(expected, actual);
    }

    public void test_insert_table_02() {
        System.out.println("Test insert table 02");
        List<O_table> expected = new ArrayList<>();
        O_table table = new O_table();
        table.setSchema_id(schema_id);
        table.setTable_id(table_id_02);
        table.setTable_name("Student");
        expected.add(table);
        table_dao.insert(table);
        List<O_table> actual = table_dao.find_02(table);
        check_tables(expected, actual);
    }

    public void test_relationship_insert() {
        System.out.println("Test insert relationship");
        List<O_relationship> expected = new ArrayList<>();
        O_relationship relationship = new O_relationship();
        relationship.setSchema_id(schema_id);
        relationship.setParent_table_id(table_id_01);
        relationship.setChild_table_id(table_id_02);
        relationship.setRelationship_id(0);
        relationship.setRelationship_type_id(0);
        expected.add(relationship);
        relationship_dao.insert(relationship);
        List<O_relationship> actual = relationship_dao.find(relationship);
        check_relationship(expected, actual);
    }

    public void test_insert_key_table_01() {
        System.out.println("Test insert key");
        List<O_key> expected_keys = new ArrayList<>();
        O_key table_key = new O_key();
        table_key.setSchema_id(schema_id);
        table_key.setTable_id(table_id_01);
        table_key.setTable_key_id(0);
        table_key.setTable_key_name("Id");
        table_key.setTable_key_label("Id");
        table_key.setTable_key_is_pk(true);
        table_key.setTable_key_type_id(0);
        table_key.setTable_key_order(0);
        key_dao.insert(table_key);
        List<O_key> actual_keys = key_dao.find_key(table_key);
        expected_keys.add(table_key);
        check_keys(expected_keys, actual_keys);
    }

    public void test_insert_key_table_02() {
        System.out.println("Test insert key");
        List<O_key> expected = new ArrayList<>();
        O_key table_key = new O_key();
        table_key.setSchema_id(schema_id);
        table_key.setTable_id(table_id_02);
        table_key.setTable_key_id(0);
        table_key.setTable_key_name("Id");
        table_key.setTable_key_label("Id");
        table_key.setTable_key_is_pk(true);
        table_key.setTable_key_type_id(0);
        table_key.setTable_key_order(0);
        expected.add(table_key);
        key_dao.insert(table_key);
        List<O_key> actual = key_dao.find_key(table_key);
        check_keys(expected, actual);
    }

    public void test_relationship_key_pair_insert() {
        System.out.println("Test key pair insert");
        List<O_relationship_key_pair> expected = new ArrayList<>();
        O_relationship_key_pair pair = new O_relationship_key_pair();
        pair.setSchema_id(schema_id);
        pair.setParent_table_id(table_id_01);
        pair.setChild_table_id(table_id_02);
        pair.setRelationship_id(0);
        pair.setParent_key_id(0);
        pair.setChild_key_id(0);
        expected.add(pair);
        key_pair_dao.insert(pair);
        //key_pair_dao.delete(pair);
    }

    private void check_schema(List<O_schema> expected, List<O_schema> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i).getSchema_id(), actual.get(i).getSchema_id());
            assertEquals(expected.get(i).getSchema_name(), actual.get(i).getSchema_name());
        }
    }

    private void check_tables(List<O_table> expected, List<O_table> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getSchema_id(), actual.get(i).getSchema_id());
            assertEquals(expected.get(i).getTable_id(), actual.get(i).getTable_id());
            assertEquals(expected.get(i).getTable_name(), actual.get(i).getTable_name());
        }
    }

    private void check_relationship(List<O_relationship> expected, List<O_relationship> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getSchema_id(), actual.get(i).getSchema_id());
            assertEquals(expected.get(i).getParent_table_id(), actual.get(i).getParent_table_id());
            assertEquals(expected.get(i).getChild_table_id(), actual.get(i).getChild_table_id());
            assertEquals(expected.get(i).getRelationship_id(), actual.get(i).getRelationship_id());
            assertEquals(expected.get(i).getRelationship_type_id(), actual.get(i).getRelationship_type_id());
        }
    }

    private void check_keys(List<O_key> expected, List<O_key> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getSchema_id(), actual.get(i).getSchema_id());
            assertEquals(expected.get(i).getTable_id(), actual.get(i).getTable_id());
            assertEquals(expected.get(i).getTable_key_id(), actual.get(i).getTable_key_id());
            assertEquals(expected.get(i).getTable_key_name(), actual.get(i).getTable_key_name());
            assertEquals(expected.get(i).getTable_key_label(), actual.get(i).getTable_key_label());
            assertEquals(expected.get(i).getTable_key_is_pk(), actual.get(i).getTable_key_is_pk());
            assertEquals(expected.get(i).getTable_key_type_id(), actual.get(i).getTable_key_type_id());
            assertEquals(expected.get(i).getTable_key_order(), actual.get(i).getTable_key_order());
        }
    }

    private void check_relationship_key_pair(List<O_relationship_key_pair> expected, List<O_relationship_key_pair> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getSchema_id(), actual.get(i).getSchema_id());
            assertEquals(expected.get(i).getParent_table_id(), actual.get(i).getParent_table_id());
            assertEquals(expected.get(i).getChild_table_id(), actual.get(i).getChild_table_id());
            assertEquals(expected.get(i).getParent_key_id(), actual.get(i).getParent_key_id());
            assertEquals(expected.get(i).getChild_key_id(), actual.get(i).getChild_key_id());
            assertEquals(expected.get(i).getRelationship_id(), actual.get(i).getRelationship_id());
        }
    }

    private void check_select_only_names(List<O_select_only_names> expResult, List<O_select_only_names> result) {
        for (int i = 0; i < expResult.size(); i++) {
            assertEquals(expResult.get(i).getParent(), result.get(i).getParent());
            assertEquals(expResult.get(i).getRelationship_id(), result.get(i).getRelationship_id());
            assertEquals(expResult.get(i).getRelationship_type_id(), result.get(i).getRelationship_type_id());
            assertEquals(expResult.get(i).getParent_key_name(), result.get(i).getParent_key_name());
            assertEquals(expResult.get(i).getChild_key_name(), result.get(i).getChild_key_name());
        }
    }

    private void check_relationship_key_pair_multiple_select(List<O_key_pair_multiple_select> expected, List<O_key_pair_multiple_select> actual) {
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getSchema_id(), actual.get(i).getSchema_id());
            assertEquals(expected.get(i).getParent_table_id(), actual.get(i).getParent_table_id());
            assertEquals(expected.get(i).getChild_table_id(), actual.get(i).getChild_table_id());
            assertEquals(expected.get(i).getRelationship_id(), actual.get(i).getRelationship_id());
            assertEquals(expected.get(i).getParent_key_id(), actual.get(i).getParent_key_id());
            assertEquals(expected.get(i).getChild_key_id(), actual.get(i).getChild_key_id());
            assertEquals(expected.get(i).getParent(), actual.get(i).getParent());
            assertEquals(expected.get(i).getChild(), actual.get(i).getChild());
        }
    }

    public void test_relationship_select_only_names() {
        System.out.println("Test relationship select only names");
        List<O_select_only_names> expResult = new ArrayList<>();
        O_select_only_names e00 = new O_select_only_names();
        e00.setParent("Class");
        e00.setChild("Student");
        e00.setRelationship_id(0);
        e00.setRelationship_type_id(0);
        e00.setParent_key_name("Id");
        e00.setChild_key_name("Id");
        expResult.add(e00);
        O_schema schema00 = new O_schema();
        schema00.setSchema_id(schema_id);
        schema00.setSchema_name("Rolodex");
        List<O_select_only_names> result = other_dao.relationship_select_only_names(schema00);
        check_select_only_names(expResult, result);
    }

    public void test_relationship_key_pair_multiple_select() {
        System.out.println("Test relationship key pair multiple select");
        O_relationship_key_pair key_pair = new O_relationship_key_pair();
        key_pair.setSchema_id(schema_id);
        key_pair.setParent_table_id(table_id_01);
        key_pair.setChild_table_id(table_id_02);
        key_pair.setRelationship_id(0);
        List<O_key_pair_multiple_select> expected = new ArrayList<>();
        O_key_pair_multiple_select e00 = new O_key_pair_multiple_select();
        e00.setSchema_id(schema_id);
        e00.setParent_table_id(table_id_01);
        e00.setChild_table_id(table_id_02);
        e00.setRelationship_id(0);
        e00.setParent_key_id(0);
        e00.setChild_key_id(0);
        e00.setParent("Id");
        e00.setChild("Id");
        expected.add(e00);
        O_relationship relationship = new O_relationship();
        relationship.setSchema_id(schema_id);
        relationship.setParent_table_id(table_id_01);
        relationship.setChild_table_id(table_id_02);
        relationship.setRelationship_id(0);
        List<O_key_pair_multiple_select> actual = other_dao.relationship_key_pair_multiple_select(relationship);
        check_relationship_key_pair_multiple_select(expected, actual);
    }

}
