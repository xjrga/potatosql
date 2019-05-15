package org.xjrga.potatosql.load;

public class LoadDataMart
{
    public LoadDataMart()
    {

        LoadDatabase loadDatabase = new LoadDatabase();

        try
        {
            loadDatabase.turnOffAutocommit();

            Integer schemaId = loadDatabase.Schema_Insert("DataMart");
            Integer tableId0 = loadDatabase.Table_Insert(schemaId, "Fact");
            Integer tableId1 = loadDatabase.Table_Insert(schemaId, "Dimension1");
            Integer tableId2 = loadDatabase.Table_Insert(schemaId, "Dimension2");
            Integer tableId3 = loadDatabase.Table_Insert(schemaId, "Dimension3");

            Integer tableId0keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Dimension1Key", "Dimension1Key", true, 1, 0, 0, 1);
            Integer tableId0keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Dimension2Key", "Dimension2Key", true, 1, 0, 0, 2);
            Integer tableId0keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Dimension3Key", "Dimension3Key", true, 1, 0, 0, 3);
            Integer tableId0keyId3 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Amount", "Amount", false, 2, 0, 0, 4);

            Integer tableId1keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Dimension1Key", "Dimension1Key", true, 1, 0, 0, 1);
            Integer tableId1keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Dimension1Attributes", "Dimension1Attributes", false, 3, 8000, 0, 2);

            Integer tableId2keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId2, "Dimension2Key", "Dimension2Key", true, 1, 0, 0, 1);
            Integer tableId2keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId2, "Dimension2Attributes", "Dimension2Attributes", false, 3, 8000, 0, 2);

            Integer tableId3keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId3, "Dimension3Key", "Dimension3Key", true, 1, 0, 0, 1);
            Integer tableId3keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId3, "Dimension3Attributes", "Dimension3Attributes", false, 3, 8000, 0, 2);

            Integer relationshipTypeId = null;
            //datedimension -> salesfact, identifying , backwards?
            Integer relationshipId0 = loadDatabase.Relationship_Insert(schemaId, tableId1, tableId0, 0, "", "", "");
            //storedimension -> salesfact, identifying
            Integer relationshipId1 = loadDatabase.Relationship_Insert(schemaId, tableId2, tableId0, 0, "", "", "");
            //productdimension -> salesfact, identifying
            Integer relationshipId2 = loadDatabase.Relationship_Insert(schemaId, tableId3, tableId0, 0, "", "", "");

            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId1, tableId0, relationshipId0, tableId1keyId0, tableId0keyId0);
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId2, tableId0, relationshipId1, tableId2keyId0, tableId0keyId1);
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId3, tableId0, relationshipId2, tableId3keyId0, tableId0keyId2);

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
        LoadDataMart loadDataMart = new LoadDataMart();
    }
}
