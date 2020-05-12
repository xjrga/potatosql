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

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class DbLink {

    private final ProcRunner procRunner;


    public DbLink() {

        procRunner = new ProcRunner();
    }


    //DatabaseSchema Table


    public void DatabaseSchema_Insert(String Name) {

        String sql = "{CALL public.DatabaseSchema_Insert(?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(Name);
        procRunner.callProcedure(sql, parameterList.toArray());
    }


    public void DatabaseSchema_Update(Integer SchemaId, String Name) {

        String sql = "{CALL public.DatabaseSchema_Update(?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(Name);
        procRunner.callProcedure(sql, parameterList.toArray());
    }


    public void DatabaseSchema_Delete(Integer SchemaId) {

        String sql = "{CALL public.DatabaseSchema_Delete(?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        procRunner.callProcedure(sql, parameterList.toArray());
    }


    public List<Map<String, Object>> DatabaseSchema_Select_All() {

        String sql = "{CALL public.DatabaseSchema_Select_All()}";
        return procRunner.callProcedureR(sql, new LinkedList().toArray());
    }


    //DatabaseTable Table


    public void DatabaseTable_Insert(Integer SchemaId, String Name) {

        String sql = "{CALL public.DatabaseTable_Insert(?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(Name);
        procRunner.callProcedure(sql, parameterList.toArray());
    }


    public void DatabaseTable_Update(Integer SchemaId, Integer TableId, String Name) {

        String sql = "{CALL public.DatabaseTable_Update(?,?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(TableId);
        parameterList.add(Name);
        procRunner.callProcedure(sql, parameterList.toArray());
    }


    public void DatabaseTable_Delete(Integer SchemaId, Integer TableId) {

        String sql = "{CALL public.DatabaseTable_Delete(?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(TableId);
        procRunner.callProcedure(sql, parameterList.toArray());
    }


    public List<Map<String, Object>> DatabaseTable_Select(Integer SchemaId) {

        String sql = "{CALL public.DatabaseTable_Select(?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        return procRunner.callProcedureR(sql, parameterList.toArray());
    }

    public List<Map<String, Object>> DatabaseTable_Select(Integer SchemaId, Integer TableId) {

        String sql = "{CALL public.DatabaseTable_Select(?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(TableId);
        return procRunner.callProcedureR(sql, parameterList.toArray());
    }


    public void TableKey_Insert(Integer SchemaId, Integer TableId, String Name, String Label, Boolean IsPk, Integer TypeId, Integer Precision, Integer Scale, Integer Order) {

        String sql = "{CALL public.TableKey_Insert(?,?,?,?,?,?,?,?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(TableId);
        parameterList.add(Name);
        parameterList.add(Label);
        parameterList.add(IsPk);
        parameterList.add(TypeId);
        parameterList.add(Precision);
        parameterList.add(Scale);
        parameterList.add(Order);
        procRunner.callProcedure(sql, parameterList.toArray());
    }


    public void TableKey_Update(Integer SchemaId, Integer TableId, Integer KeyId, String Name, String Label, Boolean IsPk, Integer TypeId, Integer Precision, Integer Scale, Integer Order) {

        String sql = "{CALL public.TableKey_Update(?,?,?,?,?,?,?,?,?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(TableId);
        parameterList.add(KeyId);
        parameterList.add(Name);
        parameterList.add(Label);
        parameterList.add(IsPk);
        parameterList.add(TypeId);
        parameterList.add(Precision);
        parameterList.add(Scale);
        parameterList.add(Order);
        procRunner.callProcedure(sql, parameterList.toArray());
    }


    public void TableKey_Delete(Integer SchemaId, Integer TableId, Integer KeyId) {

        String sql = "{CALL public.TableKey_Delete(?,?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(TableId);
        parameterList.add(KeyId);
        procRunner.callProcedure(sql, parameterList.toArray());
    }


    public List<Map<String, Object>> TableKey_Select(Integer SchemaId, Integer TableId) {

        String sql = "{CALL public.TableKey_Select(?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(TableId);
        return procRunner.callProcedureR(sql, parameterList.toArray());
    }


    public List<Map<String, Object>> TableKey_Select_PK(Integer SchemaId, Integer TableId) {

        String sql = "{CALL public.TableKey_Select_PK(?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(TableId);
        return procRunner.callProcedureR(sql, parameterList.toArray());
    }


    public List<Map<String, Object>> TableKey_Select_NPK(Integer SchemaId, Integer TableId) {

        String sql = "{CALL public.TableKey_Select_NPK(?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(TableId);
        return procRunner.callProcedureR(sql, parameterList.toArray());
    }


    //KeyType Table


    public void KeyType_Insert(Integer typeId, String Name, Boolean PrecisionRequired, Boolean ScaleRequired) {

        String sql = "{CALL public.KeyType_Insert(?,?,?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(typeId);
        parameterList.add(Name);
        parameterList.add(PrecisionRequired);
        parameterList.add(ScaleRequired);
        procRunner.callProcedure(sql, parameterList.toArray());
    }


    public void KeyType_Update(Integer TypeId, String Name, Boolean PrecisionRequired) {

        String sql = "{CALL public.KeyType_Update(?,?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(TypeId);
        parameterList.add(Name);
        parameterList.add(PrecisionRequired);
        procRunner.callProcedure(sql, parameterList.toArray());
    }


    public void KeyType_Delete(Integer TypeId) {

        String sql = "{CALL public.KeyType_Delete(?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(TypeId);
        procRunner.callProcedure(sql, parameterList.toArray());
    }


    public List<Map<String, Object>> KeyType_Select_All() {

        String sql = "{CALL public.KeyType_Select_All()}";
        return procRunner.callProcedureR(sql, new LinkedList().toArray());
    }


    //RelationshipType Table


    public void RelationshipType_Insert(Integer relationshipTypeId, String Name) {

        String sql = "{CALL public.RelationshipType_Insert(?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(relationshipTypeId);
        parameterList.add(Name);
        procRunner.callProcedure(sql, parameterList.toArray());
    }


    public void RelationshipType_Update(Integer RelationshipTypeId, String Name) {

        String sql = "{CALL public.RelationshipType_Update(?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(RelationshipTypeId);
        parameterList.add(Name);
        procRunner.callProcedure(sql, parameterList.toArray());
    }


    public void RelationshipType_Delete(Integer RelationshipTypeId) {

        String sql = "{CALL public.RelationshipType_Delete(?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(RelationshipTypeId);
        procRunner.callProcedure(sql, parameterList.toArray());
    }


    public List<Map<String, Object>> RelationshipType_Select_All() {

        String sql = "{CALL public.RelationshipType_Select_All()}";
        return procRunner.callProcedureR(sql, new LinkedList().toArray());
    }


    //Relationship Table
    public void Relationship_Insert(Integer SchemaId, Integer Parent_TableId, Integer Child_TableId, Integer RelationshipTypeId, String relationshipName, String forwardVerbPhrase, String reverseVerbPhrase) {

        String sql = "{CALL public.Relationship_Insert(?,?,?,?,?,?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(Parent_TableId);
        parameterList.add(Child_TableId);
        parameterList.add(RelationshipTypeId);
        parameterList.add(relationshipName);
        parameterList.add(forwardVerbPhrase);
        parameterList.add(reverseVerbPhrase);
        procRunner.callProcedure(sql, parameterList.toArray());
    }


    public void Relationship_Delete(Integer SchemaId, Integer Parent_TableId, Integer Child_TableId, Integer RelationshipId) {

        String sql = "{CALL public.Relationship_Delete(?,?,?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(Parent_TableId);
        parameterList.add(Child_TableId);
        parameterList.add(RelationshipId);
        procRunner.callProcedure(sql, parameterList.toArray());
    }


    public void Relationship_Update(Integer SchemaId, Integer Parent_TableId, Integer Child_TableId, Integer RelationshipId, Integer RelationshipTypeId, String relationshipName, String forwardVerbPhrase, String reverseVerbPhrase) {

        String sql = "{CALL public.Relationship_Update(?,?,?,?,?,?,?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(Parent_TableId);
        parameterList.add(Child_TableId);
        parameterList.add(RelationshipId);
        parameterList.add(RelationshipTypeId);
        parameterList.add(relationshipName);
        parameterList.add(forwardVerbPhrase);
        parameterList.add(reverseVerbPhrase);
        procRunner.callProcedure(sql, parameterList.toArray());
    }


    public List<Map<String, Object>> Relationship_Select(Integer SchemaId) {

        String sql = "{CALL public.Relationship_Select(?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        return procRunner.callProcedureR(sql, parameterList.toArray());
    }


    public List<Map<String, Object>> Relationship_Multiple_Select(Integer SchemaId) {

        String sql = "{CALL public.Relationship_Multiple_Select(?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        return procRunner.callProcedureR(sql, parameterList.toArray());
    }

    public List<Map<String, Object>> Relationship_Multiple_Select_2(Integer SchemaId, Integer TableId) {

        String sql = "{CALL public.Relationship_Multiple_Select_2(?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(TableId);
        return procRunner.callProcedureR(sql, parameterList.toArray());
    }

    public void RelationshipKeyPair_Insert(Integer SchemaId, Integer Parent_TableId, Integer Child_TableId, Integer RelationshipId, Integer Parent_KeyId, Integer Child_KeyId) {

        String sql = "{CALL public.RelationshipKeyPair_Insert(?,?,?,?,?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(Parent_TableId);
        parameterList.add(Child_TableId);
        parameterList.add(RelationshipId);
        parameterList.add(Parent_KeyId);
        parameterList.add(Child_KeyId);
        procRunner.callProcedure(sql, parameterList.toArray());
    }


    public void RelationshipKeyPair_Delete(Integer SchemaId, Integer Parent_TableId, Integer Child_TableId, Integer RelationshipId, Integer Parent_KeyId, Integer Child_KeyId) {

        String sql = "{CALL public.RelationshipKeyPair_Delete(?,?,?,?,?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(Parent_TableId);
        parameterList.add(Child_TableId);
        parameterList.add(RelationshipId);
        parameterList.add(Parent_KeyId);
        parameterList.add(Child_KeyId);
        procRunner.callProcedure(sql, parameterList.toArray());
    }

    public void RelationshipKeyPair_Delete(Integer SchemaId, Integer Parent_TableId, Integer Child_TableId, Integer RelationshipId) {

        String sql = "{CALL public.RelationshipKeyPair_Delete(?,?,?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(Parent_TableId);
        parameterList.add(Child_TableId);
        parameterList.add(RelationshipId);
        procRunner.callProcedure(sql, parameterList.toArray());
    }

    public List<Map<String, Object>> RelationshipKeyPair_Select(Integer SchemaId, Integer Parent_TableId, Integer Child_TableId, Integer RelationshipId) {

        String sql = "{CALL public.RelationshipKeyPair_Select(?,?,?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(Parent_TableId);
        parameterList.add(Child_TableId);
        parameterList.add(RelationshipId);
        return procRunner.callProcedureR(sql, parameterList.toArray());
    }

    //Other


    public void resetDB() {

        procRunner.resetDB();
    }


    //TableKey_KeyType
    public List<Map<String, Object>> TableKey_KeyType_Select(Integer SchemaId, Integer TableId) {

        String sql = "{CALL public.TableKey_KeyType_Select(?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(TableId);
        return procRunner.callProcedureR(sql, parameterList.toArray());
    }


    public List<Map<String, Object>> RelationshipKeyPair_Multiple_Select(Integer SchemaId, Integer Parent_TableId, Integer Child_TableId, Integer RelationshipId) {

        String sql = "{CALL public.RelationshipKeyPair_Multiple_Select(?,?,?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(Parent_TableId);
        parameterList.add(Child_TableId);
        parameterList.add(RelationshipId);
        return procRunner.callProcedureR(sql, parameterList.toArray());
    }


    public List<Map<String, Object>> Relationship_SelectWithNames(Integer SchemaId) {

        String sql = "{CALL public.Relationship_SelectWithNames(?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        return procRunner.callProcedureR(sql, parameterList.toArray());
    }


    public List<Map<String, Object>> Relationship_SelectOnlyNames(Integer SchemaId) {

        String sql = "{CALL public.Relationship_SelectOnlyNames(?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        return procRunner.callProcedureR(sql, parameterList.toArray());
    }


    public void Shutdown() {

        procRunner.Shutdown();
    }


    public void DatabaseSchema_Copy(Integer SchemaId, String SchemaName) {

        String sql = "{CALL public.DatabaseSchema_Copy(?,?)}";
        LinkedList<Object> parameterList = new LinkedList<>();
        parameterList.add(SchemaId);
        parameterList.add(SchemaName);
        procRunner.callProcedure(sql, parameterList.toArray());
    }


    public void PotatoSql_Table_Copy(Integer SchemaId, Integer TableId_Old, String Name) {

        String sql = "{CALL public.PotatoSql_Table_Copy(?, ?, ? )}";
        LinkedList parameterList = new LinkedList();
        parameterList.add(SchemaId);
        parameterList.add(TableId_Old);
        parameterList.add(Name);
        procRunner.callProcedure(sql, parameterList.toArray());
    }

    public void PotatoSql_TableKey_Copy(Integer SchemaId, Integer TableId, Integer KeyId, String Name) {
        String sql = "{CALL public.PotatoSql_TableKey_Copy(?, ?, ?,? )}";
        LinkedList parameterList = new LinkedList();
        parameterList.add(SchemaId);
        parameterList.add(TableId);
        parameterList.add(KeyId);
        parameterList.add(Name);
        procRunner.callProcedure(sql, parameterList.toArray());
    }

}