package org.xjrga.potatosql.load;

public class LoadRolodex
{
    public LoadRolodex()
    {

        LoadDatabase loadDatabase = new LoadDatabase();

        try
        {
            loadDatabase.turnOffAutocommit();

            Integer schemaId = loadDatabase.Schema_Insert("Rolodex");
            Integer tableId0 = loadDatabase.Table_Insert(schemaId, "Person");
            Integer tableId1 = loadDatabase.Table_Insert(schemaId, "Address");
            Integer tableId2 = loadDatabase.Table_Insert(schemaId, "Address_Usage");

            Integer tableId0keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Person_Id", "Person_Id", true, 1, 0, 0, 1);
            Integer tableId0keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Person_Name", "Person_Name", false, 3, 8000, 0, 2);

            Integer tableId1keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Address_Id", "Address_Id", true, 1, 0, 0, 1);
            Integer tableId1keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Address_Detail", "Address_Detail", false, 3, 8000, 0, 2);

            Integer tableId2keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId2, "Address_Id", "Address_Id", true, 1, 0, 0, 1);
            Integer tableId2keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId2, "Person_Id", "Person_Id", true, 1, 0, 0, 2);
            Integer tableId2keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId2, "Usage_Type", "Usage_Type", true, 3, 8000, 0, 3);
            Integer tableId2keyId3 = loadDatabase.TableKey_Insert(schemaId, tableId2, "Usage_Start_Date", "Usage_Start_Date", false, 6, 0, 0, 4);

            //person -> address_usage, non-identifying -> 0
            Integer relationshipId0 = loadDatabase.Relationship_Insert(schemaId, tableId0, tableId2, 0, "", "may use", "");
            //address -> address_usage, non-identifying -> 0
            Integer relationshipId1 = loadDatabase.Relationship_Insert(schemaId, tableId1, tableId2, 0, "", "used by", "");

            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId0, tableId2, relationshipId0, tableId0keyId0, tableId2keyId1);
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId1, tableId2, relationshipId1, tableId1keyId0, tableId2keyId0);

            loadDatabase.turnOnAutocommit();
        }
        catch (Exception e)
        {
            loadDatabase.rollback();
        }
        finally
        {
            loadDatabase.shutdown();
        }

    }


    public static void main(String[] args)
    {
        LoadRolodex loadRolodex = new LoadRolodex();
    }
}
