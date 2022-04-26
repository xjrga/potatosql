package io.github.xjrga.potatosql.data.dao.implementation;

import io.github.xjrga.potatosql.data.dao.impl.Schema_dao_impl;
import io.github.xjrga.potatosql.data.dao.impl.Table_dao_impl;
import io.github.xjrga.potatosql.data.dao.impl.Relationship_dao_impl;
import io.github.xjrga.potatosql.data.dao.impl.Other_dao_impl;
import io.github.xjrga.potatosql.data.Connections;
import io.github.xjrga.potatosql.data.Dbactions;
import io.github.xjrga.potatosql.data.dto.O_relationship;
import io.github.xjrga.potatosql.data.dto.O_relationship_multiple_select;
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
public class Relationship_dao_implTest {

    private static Connection connection;
    private Schema_dao_impl schema_dao;
    private Table_dao_impl table_dao;
    private Relationship_dao_impl relationship_dao;
    private Other_dao_impl other_dao;
    private final int schema_id = 1;
    private final int table_id_01 = 0;
    private final int table_id_02 = 1;

    public Relationship_dao_implTest() {
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
        other_dao = new Other_dao_impl(connection);
    }

    @After
    public void tearDown() {
    }

    @Ignore
    public void test() {
        test_insert_schema();
        test_insert_table_01();
        test_insert_table_02();
        test_relationship_insert();
        test_relationship_multiple_select();
        test_delete_relationship();
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

    public void test_delete_relationship() {
        System.out.println("Test delete relationship");
        List<O_relationship> expected = new ArrayList<>();
        O_relationship relationship = new O_relationship();
        relationship.setSchema_id(schema_id);
        relationship.setParent_table_id(table_id_01);
        relationship.setChild_table_id(table_id_02);
        relationship.setRelationship_id(0);
        relationship_dao.delete(relationship);
        List<O_relationship> actual = relationship_dao.find(relationship);
        check_relationship(expected, actual);
    }

    public void test_relationship_multiple_select() {
        System.out.println("Test relationship multiple select");
        List<O_relationship_multiple_select> expected = new ArrayList<>();
        O_relationship_multiple_select e00 = new O_relationship_multiple_select();
        e00.setSchema_id(schema_id);
        e00.setParent_table_id(table_id_01);
        e00.setParent("Class");
        e00.setChild_table_id(table_id_02);
        e00.setChild("Student");
        e00.setRelationship_type_id(0);
        e00.setRelationship_id(0);
        e00.setRelationship_type_name("Identifying");
        expected.add(e00);
        O_schema schema00 = new O_schema();
        schema00.setSchema_id(schema_id);
        List<O_relationship_multiple_select> result = other_dao.relationship_multiple_select(schema00);
        check_relationship_multiple_select(expected, result);
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

    private void check_relationship_multiple_select(List<O_relationship_multiple_select> expected, List<O_relationship_multiple_select> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getSchema_id(), actual.get(i).getSchema_id());
            assertEquals(expected.get(i).getParent_table_id(), actual.get(i).getParent_table_id());
            assertEquals(expected.get(i).getParent(), actual.get(i).getParent());
            assertEquals(expected.get(i).getChild_table_id(), actual.get(i).getChild_table_id());
            assertEquals(expected.get(i).getChild(), actual.get(i).getChild());
            assertEquals(expected.get(i).getRelationship_type_id(), actual.get(i).getRelationship_type_id());
            assertEquals(expected.get(i).getRelationship_id(), actual.get(i).getRelationship_id());
            assertEquals(expected.get(i).getRelationship_type_name(), actual.get(i).getRelationship_type_name());
        }
    }

}
