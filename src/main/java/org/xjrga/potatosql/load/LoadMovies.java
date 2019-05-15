package org.xjrga.potatosql.load;

public class LoadMovies
{
    public LoadMovies()
    {

        LoadDatabase loadDatabase = new LoadDatabase();

        try
        {
            loadDatabase.turnOffAutocommit();

            Integer schemaId = loadDatabase.Schema_Insert("Movies");
            Integer tableId0 = loadDatabase.Table_Insert(schemaId, "Actor");
            Integer tableId1 = loadDatabase.Table_Insert(schemaId, "Film");
            Integer tableId2 = loadDatabase.Table_Insert(schemaId, "Category");

            Integer tableId0keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Actor_Id", "Actor_Id", true, 1, 0, 0, 1);
            Integer tableId0keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Name", "Name", false, 3, 8000, 0, 2);
            Integer tableId0keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Film_Id", "Film_Id", false, 1, 0, 0, 3);

            Integer tableId1keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Film_Id", "Film_Id", true, 1, 0, 0, 1);
            Integer tableId1keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Title", "Title", false, 3, 8000, 0, 2);
            Integer tableId1keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Description", "Description", false, 3, 8000, 0, 3);
            Integer tableId1keyId3 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Release_Year", "Release_Year", false, 1, 0, 0, 4);
            Integer tableId1keyId4 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Length", "Length", false, 1, 0, 0, 5);
            Integer tableId1keyId5 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Rating", "Rating", false, 1, 0, 0, 6);
            Integer tableId1keyId6 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Category_Id", "Category_Id", false, 1, 0, 0, 7);

            Integer tableId2keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId2, "Category_Id", "Category_Id", true, 1, 0, 0, 1);
            Integer tableId2keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId2, "Name", "Name", false, 3, 8000, 0, 2);

            Integer relationshipTypeId = null;
            //film -> actor, non-identifying -> 1
            Integer relationshipId0 = loadDatabase.Relationship_Insert(schemaId, tableId1, tableId0, 1, "", "", "");
            //film category -> firm, non-identifying -> 1
            Integer relationshipId1 = loadDatabase.Relationship_Insert(schemaId, tableId2, tableId1, 1, "", "", "");

            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId1, tableId0, relationshipId0, tableId1keyId0, tableId0keyId2);
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId2, tableId1, relationshipId1, tableId2keyId0, tableId1keyId6);

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
        LoadMovies loadMovies = new LoadMovies();
    }
}
