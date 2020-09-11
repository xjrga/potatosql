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
package org.xjrga.potatosql.data;

import org.xjrga.potatosql.dataobject.*;
import org.xjrga.potatosql.other.Log;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DbLink {
    private final Connection connection;

    public DbLink() {
        connection = Connect.getInstance().getConnection();
    }

    public DatabaseSchemaDataObject DatabaseSchema_Insert(DatabaseSchemaDataObject databaseschemaDataObject) throws SQLException {
        CallableStatement proc = connection.prepareCall("{CALL Public.DatabaseSchema_Insert( ?, ? )}");
        proc.registerOutParameter(1, Types.INTEGER);
        proc.setString(2, databaseschemaDataObject.getName());
        proc.execute();
        databaseschemaDataObject.setSchemaId(proc.getInt(1));
        proc.close();
        return databaseschemaDataObject;
    }

    public void DatabaseSchema_Update(DatabaseSchemaDataObject databaseschemaDataObject) throws SQLException {
        CallableStatement proc = connection.prepareCall("{CALL Public.DatabaseSchema_Update( ?, ? )}");
        proc.setInt(1, databaseschemaDataObject.getSchemaId());
        proc.setString(2, databaseschemaDataObject.getName());
        proc.execute();
        proc.close();
    }

    public void DatabaseSchema_Delete(DatabaseSchemaDataObject databaseschemaDataObject) throws SQLException {
        CallableStatement proc = connection.prepareCall("{CALL Public.DatabaseSchema_Delete( ? )}");
        proc.setInt(1, databaseschemaDataObject.getSchemaId());
        proc.execute();
        proc.close();
    }

    public List<DatabaseSchemaDataObject> DatabaseSchema_Select_All() throws SQLException {
        List<DatabaseSchemaDataObject> list = new LinkedList<>();
        CallableStatement proc;
        proc = connection.prepareCall("{CALL Public.DatabaseSchema_Select_All()}");
        ResultSet rs = proc.executeQuery();
        while (rs.next()) {
            DatabaseSchemaDataObject databaseschemaDataObject = new DatabaseSchemaDataObject();
            databaseschemaDataObject.setSchemaId(rs.getInt(1));
            databaseschemaDataObject.setName(rs.getString(2));
            list.add(databaseschemaDataObject);
        }
        proc.close();
        return list;
    }

    public DatabaseTableDataObject DatabaseTable_Insert(DatabaseTableDataObject databasetableDataObject) throws SQLException {
        CallableStatement proc = connection.prepareCall("{CALL Public.DatabaseTable_Insert( ?, ?, ? )}");
        proc.setInt(1, databasetableDataObject.getSchemaId());
        proc.registerOutParameter(2, Types.INTEGER);
        proc.setString(3, databasetableDataObject.getName());
        proc.execute();
        databasetableDataObject.setTableId(proc.getInt(2));
        proc.close();
        return databasetableDataObject;
    }

    public void DatabaseTable_Update(DatabaseTableDataObject databasetableDataObject) throws SQLException {
        CallableStatement proc = connection.prepareCall("{CALL Public.DatabaseTable_Update( ?, ?, ? )}");
        proc.setInt(1, databasetableDataObject.getSchemaId());
        proc.setInt(2, databasetableDataObject.getTableId());
        proc.setString(3, databasetableDataObject.getName());
        proc.execute();
        proc.close();
    }

    public void DatabaseTable_Delete(DatabaseTableDataObject databasetableDataObject) throws SQLException {
        CallableStatement proc = connection.prepareCall("{CALL Public.DatabaseTable_Delete( ?, ? )}");
        proc.setInt(1, databasetableDataObject.getSchemaId());
        proc.setInt(2, databasetableDataObject.getTableId());
        proc.execute();
        proc.close();
    }

    public List<DatabaseTableDataObject> DatabaseTable_Select(DatabaseSchemaDataObject databaseSchemaDataObject) throws SQLException {
        LinkedList<DatabaseTableDataObject> list = new LinkedList<>();
        CallableStatement proc;
        proc = connection.prepareCall("{CALL Public.DatabaseTable_Select(?)}");
        proc.setInt(1, databaseSchemaDataObject.getSchemaId());
        ResultSet rs = proc.executeQuery();
        while (rs.next()) {
            DatabaseTableDataObject databaseTableDataObjectOut = new DatabaseTableDataObject();
            databaseTableDataObjectOut.setSchemaId(rs.getInt(1));
            databaseTableDataObjectOut.setTableId(rs.getInt(2));
            databaseTableDataObjectOut.setName(rs.getString(3));
            list.add(databaseTableDataObjectOut);
        }
        proc.close();
        return list;
    }

    public List<DatabaseTableDataObject> DatabaseTable_Select(DatabaseTableDataObject databasetableDataObject) throws SQLException {
        LinkedList<DatabaseTableDataObject> list = new LinkedList<>();
        CallableStatement proc;
        proc = connection.prepareCall("{CALL Public.DatabaseTable_Select( ?, ? )}");
        proc.setInt(1, databasetableDataObject.getSchemaId());
        proc.setInt(2, databasetableDataObject.getTableId());
        ResultSet rs = proc.executeQuery();
        while (rs.next()) {
            databasetableDataObject.setSchemaId(rs.getInt(1));
            databasetableDataObject.setTableId(rs.getInt(2));
            databasetableDataObject.setName(rs.getString(3));
            list.add(databasetableDataObject);
        }
        proc.close();
        return list;
    }

    public TableKeyDataObject TableKey_Insert(TableKeyDataObject tablekeyDataObject) throws SQLException {
        CallableStatement proc = connection.prepareCall("{CALL Public.TableKey_Insert(?,?,?,?,?,?,?,?,?,?)}");
        proc.registerOutParameter(1, Types.INTEGER);
        proc.setInt(2, tablekeyDataObject.getSchemaId());
        proc.setInt(3, tablekeyDataObject.getTableId());
        proc.setString(4, tablekeyDataObject.getName());
        proc.setString(5, tablekeyDataObject.getLabel());
        proc.setObject(6, tablekeyDataObject.getIsPK());
        proc.setInt(7, tablekeyDataObject.getTypeId());
        proc.setInt(8, tablekeyDataObject.getPrcsn());
        proc.setInt(9, tablekeyDataObject.getScale());
        proc.setInt(10, tablekeyDataObject.getOrden());
        proc.execute();
        tablekeyDataObject.setKeyId(proc.getInt(1));
        proc.close();
        return tablekeyDataObject;
    }

    public void TableKey_Update(TableKeyDataObject tablekeyDataObject) throws SQLException {
        CallableStatement proc = connection.prepareCall("{CALL Public.TableKey_Update(?,?,?,?,?,?,?,?,?,?)}");
        proc.setInt(1, tablekeyDataObject.getSchemaId());
        proc.setInt(2, tablekeyDataObject.getTableId());
        proc.setInt(3, tablekeyDataObject.getKeyId());
        proc.setString(4, tablekeyDataObject.getName());
        proc.setString(5, tablekeyDataObject.getLabel());
        proc.setBoolean(6, tablekeyDataObject.getIsPK());
        proc.setInt(7, tablekeyDataObject.getTypeId());
        proc.setInt(8, tablekeyDataObject.getPrcsn());
        proc.setInt(9, tablekeyDataObject.getScale());
        proc.setInt(10, tablekeyDataObject.getOrden());
        proc.execute();
        proc.close();
    }

    public void TableKey_Delete(TableKeyDataObject tablekeyDataObject) throws SQLException {
        CallableStatement proc = connection.prepareCall("{CALL Public.TableKey_Delete( ?, ?, ? )}");
        proc.setInt(1, tablekeyDataObject.getSchemaId());
        proc.setInt(2, tablekeyDataObject.getTableId());
        proc.setInt(3, tablekeyDataObject.getKeyId());
        proc.execute();
        proc.close();
    }

    public List<TableKeyDataObject> TableKey_Select_PK(DatabaseTableDataObject databaseTableDataObject) throws SQLException {
        LinkedList<TableKeyDataObject> list = new LinkedList<>();
        CallableStatement proc;
        proc = connection.prepareCall("{CALL Public.TableKey_Select_PK( ?, ?)}");
        proc.setInt(1, databaseTableDataObject.getSchemaId());
        proc.setInt(2, databaseTableDataObject.getTableId());
        ResultSet rs = proc.executeQuery();
        while (rs.next()) {
            TableKeyDataObject tableKeyDataObject = new TableKeyDataObject();
            tableKeyDataObject.setSchemaId(rs.getInt(1));
            tableKeyDataObject.setTableId(rs.getInt(2));
            tableKeyDataObject.setKeyId(rs.getInt(3));
            tableKeyDataObject.setName(rs.getString(4));
            tableKeyDataObject.setLabel(rs.getString(5));
            tableKeyDataObject.setIsPK(rs.getBoolean(6));
            tableKeyDataObject.setTypeId(rs.getInt(7));
            tableKeyDataObject.setPrcsn(rs.getInt(8));
            tableKeyDataObject.setScale(rs.getInt(9));
            tableKeyDataObject.setOrden(rs.getInt(10));
            list.add(tableKeyDataObject);
        }
        proc.close();
        return list;
    }

    public List<TableKeyDataObject> TableKey_Select_NPK(DatabaseTableDataObject databaseTableDataObject) throws SQLException {
        LinkedList<TableKeyDataObject> list = new LinkedList<>();
        CallableStatement proc;
        proc = connection.prepareCall("{CALL Public.TableKey_Select_NPK(?,?)}");
        proc.setInt(1, databaseTableDataObject.getSchemaId());
        proc.setInt(2, databaseTableDataObject.getTableId());
        ResultSet rs = proc.executeQuery();
        while (rs.next()) {
            TableKeyDataObject tableKeyDataObject = new TableKeyDataObject();
            tableKeyDataObject.setSchemaId(rs.getInt(1));
            tableKeyDataObject.setTableId(rs.getInt(2));
            tableKeyDataObject.setKeyId(rs.getInt(3));
            tableKeyDataObject.setName(rs.getString(4));
            tableKeyDataObject.setLabel(rs.getString(5));
            tableKeyDataObject.setIsPK(rs.getBoolean(6));
            tableKeyDataObject.setTypeId(rs.getInt(7));
            tableKeyDataObject.setPrcsn(rs.getInt(8));
            tableKeyDataObject.setScale(rs.getInt(9));
            tableKeyDataObject.setOrden(rs.getInt(10));
            list.add(tableKeyDataObject);
        }
        proc.close();
        return list;
    }

    public List<KeyTypeDataObject> KeyType_Select_All() throws SQLException {
        List<KeyTypeDataObject> list = new LinkedList<>();
        CallableStatement proc;
        proc = connection.prepareCall("{CALL Public.KeyType_Select_All()}");
        ResultSet rs = proc.executeQuery();
        while (rs.next()) {
            KeyTypeDataObject keytypeDataObject = new KeyTypeDataObject();
            keytypeDataObject.setTypeId(rs.getInt(1));
            keytypeDataObject.setName(rs.getString(2));
            keytypeDataObject.setPrecisionRequired(rs.getBoolean(3));
            keytypeDataObject.setScaleRequired(rs.getBoolean(4));
            list.add(keytypeDataObject);
        }
        proc.close();
        return list;
    }

    public RelationshipDataObject Relationship_Insert(RelationshipDataObject relationshipDataObject) throws SQLException {
        CallableStatement proc = connection.prepareCall("{CALL Public.Relationship_Insert( ?, ?, ?, ?, ?, ?, ?, ? )}");
        proc.registerOutParameter(1, Types.INTEGER);
        proc.setInt(2, relationshipDataObject.getSchemaId());
        proc.setInt(3, relationshipDataObject.getParent_TableId());
        proc.setInt(4, relationshipDataObject.getChild_TableId());
        proc.setInt(5, relationshipDataObject.getRelationshipTypeId());
        proc.setString(6, relationshipDataObject.getName());
        proc.setString(7, relationshipDataObject.getForwardVerbPhrase());
        proc.setString(8, relationshipDataObject.getReverseVerbPhrase());
        proc.execute();
        relationshipDataObject.setRelationshipId(proc.getInt(1));
        proc.close();
        return relationshipDataObject;
    }

    public void Relationship_Delete(RelationshipDataObject relationshipDataObject) throws SQLException {
        CallableStatement proc = connection.prepareCall("{CALL Public.Relationship_Delete( ?, ?, ?, ? )}");
        proc.setInt(1, relationshipDataObject.getSchemaId());
        proc.setInt(2, relationshipDataObject.getParent_TableId());
        proc.setInt(3, relationshipDataObject.getChild_TableId());
        proc.setInt(4, relationshipDataObject.getRelationshipId());
        proc.execute();
        proc.close();
    }

    public void Relationship_Update(RelationshipDataObject relationshipDataObject) throws SQLException {
        CallableStatement proc = connection.prepareCall("{CALL Public.Relationship_Update( ?, ?, ?, ?, ?, ?, ?, ? )}");
        proc.setInt(1, relationshipDataObject.getSchemaId());
        proc.setInt(2, relationshipDataObject.getParent_TableId());
        proc.setInt(3, relationshipDataObject.getChild_TableId());
        proc.setInt(4, relationshipDataObject.getRelationshipId());
        proc.setInt(5, relationshipDataObject.getRelationshipTypeId());
        proc.setString(6, relationshipDataObject.getName());
        proc.setString(7, relationshipDataObject.getForwardVerbPhrase());
        proc.setString(8, relationshipDataObject.getReverseVerbPhrase());
        proc.execute();
        proc.close();
    }

    public List<RelationshipDataObjectExtra> Relationship_Multiple_Select(DatabaseTableDataObject databaseTableDataObject) throws SQLException {
        LinkedList<RelationshipDataObjectExtra> list = new LinkedList<>();
        CallableStatement proc;
        proc = connection.prepareCall("{CALL Relationship_Multiple_Select(?,?)}");
        proc.setInt(1, databaseTableDataObject.getSchemaId());
        proc.setInt(2, databaseTableDataObject.getTableId());
        ResultSet rs = proc.executeQuery();
        while (rs.next()) {
            RelationshipDataObjectExtra relationshipDataObjectExtra = new RelationshipDataObjectExtra();
            relationshipDataObjectExtra.setSchemaId(rs.getInt(1));
            relationshipDataObjectExtra.setParent_TableId(rs.getInt(2));
            relationshipDataObjectExtra.setParentName(rs.getString(3));
            relationshipDataObjectExtra.setChild_TableId(rs.getInt(4));
            relationshipDataObjectExtra.setChildName(rs.getString(5));
            relationshipDataObjectExtra.setRelationshipTypeId(rs.getInt(6));
            relationshipDataObjectExtra.setRelationshipId(rs.getInt(7));
            relationshipDataObjectExtra.setRelationshipTypeName(rs.getString(8));
            relationshipDataObjectExtra.setRelationshipName(rs.getString(9));
            relationshipDataObjectExtra.setForwardVerbPhrase(rs.getString(10));
            relationshipDataObjectExtra.setReverseVerbPhrase(rs.getString(11));
            list.add(relationshipDataObjectExtra);
        }
        proc.close();
        return list;
    }

    public void RelationshipKeyPair_Insert(TableKeyRelationshipKeyPairDataObject tableKeyRelationshipKeyPairDataObject) throws SQLException {
        CallableStatement proc = connection.prepareCall("{CALL public.RelationshipKeyPair_Insert(?,?,?,?,?,?)}");
        proc.setInt(1, tableKeyRelationshipKeyPairDataObject.getSchemaId());
        proc.setInt(2, tableKeyRelationshipKeyPairDataObject.getParent_TableId());
        proc.setInt(3, tableKeyRelationshipKeyPairDataObject.getChild_TableId());
        proc.setInt(4, tableKeyRelationshipKeyPairDataObject.getRelationshipId());
        proc.setInt(5, tableKeyRelationshipKeyPairDataObject.getParent_KeyId());
        proc.setInt(6, tableKeyRelationshipKeyPairDataObject.getChild_KeyId());
        proc.execute();
        proc.close();
    }

    public void RelationshipKeyPair_Delete(TableKeyRelationshipKeyPairDataObject tableKeyRelationshipKeyPairDataObject) throws SQLException {
        CallableStatement proc = connection.prepareCall("{CALL public.RelationshipKeyPair_Delete(?,?,?,?,?,?)}");
        proc.setInt(1, tableKeyRelationshipKeyPairDataObject.getSchemaId());
        proc.setInt(2, tableKeyRelationshipKeyPairDataObject.getParent_TableId());
        proc.setInt(3, tableKeyRelationshipKeyPairDataObject.getChild_TableId());
        proc.setInt(4, tableKeyRelationshipKeyPairDataObject.getRelationshipId());
        proc.setInt(5, tableKeyRelationshipKeyPairDataObject.getParent_KeyId());
        proc.setInt(6, tableKeyRelationshipKeyPairDataObject.getChild_KeyId());
        proc.execute();
        proc.close();
    }

    public List<TableKeyKeyTypeDataObject> TableKey_KeyType_Select(DatabaseTableDataObject databaseTableDataObject) throws SQLException {
        LinkedList<TableKeyKeyTypeDataObject> list = new LinkedList<>();
        CallableStatement proc;
        proc = connection.prepareCall("{CALL TableKey_KeyType_Select(?,?)}");
        proc.setInt(1, databaseTableDataObject.getSchemaId());
        proc.setInt(2, databaseTableDataObject.getTableId());
        ResultSet rs = proc.executeQuery();
        while (rs.next()) {
            TableKeyKeyTypeDataObject tableKeyKeyTypeDataObject = new TableKeyKeyTypeDataObject();
            tableKeyKeyTypeDataObject.setSchemaId(rs.getInt(1));
            tableKeyKeyTypeDataObject.setTableId(rs.getInt(2));
            tableKeyKeyTypeDataObject.setKeyId(rs.getInt(3));
            tableKeyKeyTypeDataObject.setKeyName(rs.getString(4));
            tableKeyKeyTypeDataObject.setLabel(rs.getString(5));
            tableKeyKeyTypeDataObject.setIsPK(rs.getBoolean(6));
            tableKeyKeyTypeDataObject.setTypeId(rs.getInt(7));
            tableKeyKeyTypeDataObject.setTypeName(rs.getString(8));
            tableKeyKeyTypeDataObject.setIdentity(rs.getBoolean(9));
            tableKeyKeyTypeDataObject.setPrecisionRequired(rs.getBoolean(10));
            tableKeyKeyTypeDataObject.setPrcsn(rs.getInt(11));
            tableKeyKeyTypeDataObject.setScaleRequired(rs.getBoolean(12));
            tableKeyKeyTypeDataObject.setScale(rs.getInt(13));
            tableKeyKeyTypeDataObject.setOrden(rs.getInt(14));
            list.add(tableKeyKeyTypeDataObject);
        }
        proc.close();
        return list;
    }

    public List<TableKeyRelationshipKeyPairDataObject> RelationshipKeyPair_Multiple_Select(RelationshipDataObject relationshipDataObject) throws SQLException {
        LinkedList<TableKeyRelationshipKeyPairDataObject> list = new LinkedList<>();
        CallableStatement proc;
        proc = connection.prepareCall("{CALL public.RelationshipKeyPair_Multiple_Select(?,?,?,?)}");
        proc.setInt(1, relationshipDataObject.getSchemaId());
        proc.setInt(2, relationshipDataObject.getParent_TableId());
        proc.setInt(3, relationshipDataObject.getChild_TableId());
        proc.setInt(4, relationshipDataObject.getRelationshipId());
        ResultSet rs = proc.executeQuery();
        while (rs.next()) {
            TableKeyRelationshipKeyPairDataObject tableKeyRelationshipKeyPairDataObject = new TableKeyRelationshipKeyPairDataObject();
            tableKeyRelationshipKeyPairDataObject.setSchemaId(rs.getInt(1));
            tableKeyRelationshipKeyPairDataObject.setParent_TableId(rs.getInt(2));
            tableKeyRelationshipKeyPairDataObject.setChild_TableId(rs.getInt(3));
            tableKeyRelationshipKeyPairDataObject.setRelationshipId(rs.getInt(4));
            tableKeyRelationshipKeyPairDataObject.setParent_KeyId(rs.getInt(5));
            tableKeyRelationshipKeyPairDataObject.setChild_KeyId(rs.getInt(6));
            tableKeyRelationshipKeyPairDataObject.setParentName(rs.getString(7));
            tableKeyRelationshipKeyPairDataObject.setChildName(rs.getString(8));
            list.add(tableKeyRelationshipKeyPairDataObject);
        }
        proc.close();
        return list;
    }

    public List<TableKeyRelationshipKeyPairDataObject> Relationship_SelectOnlyNames(DatabaseSchemaDataObject databaseSchemaDataObject) throws SQLException {
        List<TableKeyRelationshipKeyPairDataObject> list = new LinkedList<>();
        CallableStatement proc;
        proc = connection.prepareCall("{CALL public.Relationship_SelectOnlyNames(?)}");
        proc.setInt(1, databaseSchemaDataObject.getSchemaId());
        ResultSet rs = proc.executeQuery();
        while (rs.next()) {
            TableKeyRelationshipKeyPairDataObject tableKeyRelationshipKeyPairDataObject = new TableKeyRelationshipKeyPairDataObject();
            tableKeyRelationshipKeyPairDataObject.setParentName(rs.getString(1));
            tableKeyRelationshipKeyPairDataObject.setChildName(rs.getString(2));
            tableKeyRelationshipKeyPairDataObject.setRelationshipId(rs.getInt(3));
            tableKeyRelationshipKeyPairDataObject.setRelationshipTypeId(rs.getInt(4));
            tableKeyRelationshipKeyPairDataObject.setParentKeyName(rs.getString(5));
            tableKeyRelationshipKeyPairDataObject.setChildKeyName(rs.getString(6));
            list.add(tableKeyRelationshipKeyPairDataObject);
        }
        proc.close();
        return list;
    }

    public void DatabaseSchema_Copy(Integer SchemaId, String SchemaName) throws SQLException {
        CallableStatement proc = connection.prepareCall("{CALL public.DatabaseSchema_Copy(?,?)}");
        proc.setInt(1, SchemaId);
        proc.setString(2, SchemaName);
        proc.execute();
        proc.close();
    }

    public void PotatoSql_Table_Copy(DatabaseTableDataObject databaseTableDataObject, String Name) throws SQLException {
        CallableStatement proc = connection.prepareCall("{CALL public.PotatoSql_Table_Copy(?, ?, ? )}");
        proc.setInt(1, databaseTableDataObject.getSchemaId());
        proc.setInt(2, databaseTableDataObject.getTableId());
        proc.setString(3, Name);
        proc.execute();
        proc.close();
    }

    public void PotatoSql_TableKey_Copy(TableKeyDataObject tableKeyDataObject, String Name) throws SQLException {
        CallableStatement proc = connection.prepareCall("{CALL public.PotatoSql_TableKey_Copy(?, ?, ?,? )}");
        proc.setInt(1, tableKeyDataObject.getSchemaId());
        proc.setInt(2, tableKeyDataObject.getTableId());
        proc.setInt(3, tableKeyDataObject.getKeyId());
        proc.setString(4, Name);
        proc.execute();
        proc.close();
    }

    public void Shutdown() {
        //SHUTDOWN COMPACT
        String sql = "SHUTDOWN COMPACT;";
        Statement stmt;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            Log.getLog().start("files/exception.log");
            Log.getLog().logMessage(e.toString());
            Log.getLog().write();
            Log.getLog().close();
            e.printStackTrace();
        }
    }

    public void TableKey_Renumber(TableKeyDataObject tableKeyDataObject) throws SQLException{
        CallableStatement proc = connection.prepareCall("{CALL public.TableKey_Renumber(?, ?, ?)}");
        proc.setInt(1, tableKeyDataObject.getSchemaId());
        proc.setInt(2, tableKeyDataObject.getTableId());
        proc.setInt(3, tableKeyDataObject.getKeyId());
        proc.execute();
        proc.close();
    }
}