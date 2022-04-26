/*
 * Snack: Nutritional Software
 * Copyright (C) 2018 Jorge R Garcia de Alba
 * License: http://www.gnu.org/licenses/gpl.html GPL version 2 or higher
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package io.github.xjrga.potatosql.data;

import io.github.xjrga.potatosql.data.dao.impl.Key_dao_impl;
import io.github.xjrga.potatosql.data.dao.impl.Key_type_dao_impl;
import io.github.xjrga.potatosql.data.dao.impl.Other_dao_impl;
import io.github.xjrga.potatosql.data.dao.impl.Relationship_dao_impl;
import io.github.xjrga.potatosql.data.dao.impl.Relationship_key_pair_dao_impl;
import io.github.xjrga.potatosql.data.dao.impl.Schema_dao_impl;
import io.github.xjrga.potatosql.data.dao.impl.Table_dao_impl;
import io.github.xjrga.potatosql.data.dto.O_key;
import io.github.xjrga.potatosql.data.dto.O_key_pair_multiple_select;
import io.github.xjrga.potatosql.data.dto.O_key_type;
import io.github.xjrga.potatosql.data.dto.O_key_with_name;
import io.github.xjrga.potatosql.data.dto.O_relationship;
import io.github.xjrga.potatosql.data.dto.O_relationship_key_pair;
import io.github.xjrga.potatosql.data.dto.O_relationship_multiple_select;
import io.github.xjrga.potatosql.data.dto.O_schema;
import io.github.xjrga.potatosql.data.dto.O_select_only_names;
import io.github.xjrga.potatosql.data.dto.O_table;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Dblink {

    private static Connection connection;
    private final Schema_dao_impl schema_dao;
    private final Table_dao_impl table_dao;
    private final Relationship_dao_impl relationship_dao;
    private final Key_dao_impl key_dao;
    private final Relationship_key_pair_dao_impl key_pair_dao;
    private final Other_dao_impl other_dao;
    private final Key_type_dao_impl key_type_dao;

    public Dblink() {
        connection = Connections.PRODUCTION.get_connection();
        //connection = Connections.TESTING.get_connection();
        //connection = Connections.LOCALHOST.get_connection();
        schema_dao = new Schema_dao_impl(connection);
        table_dao = new Table_dao_impl(connection);
        relationship_dao = new Relationship_dao_impl(connection);
        key_dao = new Key_dao_impl(connection);
        key_pair_dao = new Relationship_key_pair_dao_impl(connection);
        other_dao = new Other_dao_impl(connection);
        key_type_dao = new Key_type_dao_impl(connection);
    }

    public List<O_schema> schema_select_all() {
        return schema_dao.find_all(new O_schema());
    }

    public List<O_key_type> key_type_select_all() {
        return key_type_dao.find_all(new O_key_type());
    }

    public List<O_table> table_select(O_table table) {
        return table_dao.find_all(table);
    }

    public List<O_table> table_select_02(O_table table) {
        return table_dao.find_02(table);
    }

    public List<O_key_with_name> key_type_select(O_key_with_name key) {
        return other_dao.table_key_select_with_name(key);
    }

    public List<O_select_only_names> relationship_select_only_names(O_schema schema) {
        return other_dao.relationship_select_only_names(schema);
    }

    public void schema_insert(O_schema schema) {
        schema_dao.insert(schema);
    }

    public Integer generate_id() {
        return other_dao.generate_id();
    }

    public void schema_delete(O_schema schema) {
        schema_dao.delete(schema);
    }

    public void schema_update(O_schema schema) {
        schema_dao.update(schema);
    }

    public void table_insert(O_table table) {
        table_dao.insert(table);
    }

    public void table_delete(O_table t) {
        table_dao.delete(t);
    }

    public List<O_relationship_multiple_select> relationship_multiple_select(O_schema schema) {
        return other_dao.relationship_multiple_select(schema);
    }

    public void table_update(O_table t) {
        table_dao.update(t);
    }

    public void key_insert(O_key key) {
        key_dao.insert(key);
    }

    public void key_update(O_key key) {
        key_dao.update(key);
    }

    public void key_delete(O_key key) {
        key_dao.delete(key);
    }

    public List<O_key_pair_multiple_select> relationship_key_pair_multiple_select(O_relationship o) {
        return other_dao.relationship_key_pair_multiple_select(o);
    }

    public void relationship_insert(O_relationship relationship) {
        relationship_dao.insert(relationship);
    }

    public void relationship_delete(O_relationship relationship) {
        relationship_dao.delete(relationship);
    }

    public List<O_key> find_pk(O_key o) {
        return key_dao.find_pk(o);
    }

    public List<O_key> find_npk(O_key o) {
        return key_dao.find_npk(o);
    }

    public void relationship_key_pair_insert(O_relationship_key_pair pair) {
        key_pair_dao.insert(pair);
    }

    public void relationship_key_pair_delete(O_relationship_key_pair pair) {
        key_pair_dao.delete(pair);
    }

    public void shutdown() {
        String sql = "SHUTDOWN COMPACT;";
        Statement stmt;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
        }
    }

    public void schema_copy(O_schema schema) {
        O_schema newer = new O_schema();
        newer.setSchema_id(other_dao.generate_id());
        newer.setSchema_name(schema.getSchema_name());
        other_dao.schema_copy(schema, newer);
    }

    public void table_copy(O_table table) {
        O_table newer = new O_table();
        newer.setSchema_id(table.getSchema_id());
        newer.setTable_id(other_dao.generate_id());
        newer.setTable_name(table.getTable_name());
        other_dao.table_copy(table, newer);
    }

    public String export_schema(O_schema schema) {
        return other_dao.export_schema(schema);
    }

}
