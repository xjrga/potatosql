package org.xjrga.potatosql.load;

public class LoadProject
{
    public LoadProject()
    {

        LoadDatabase loadDatabase = new LoadDatabase();

        try
        {
            loadDatabase.turnOffAutocommit();

            Integer schemaId = loadDatabase.Schema_Insert("Project");
            Integer tableId0 = loadDatabase.Table_Insert(schemaId, "Employee");
            Integer tableId1 = loadDatabase.Table_Insert(schemaId, "Project");
            Integer tableId2 = loadDatabase.Table_Insert(schemaId, "Project_Employment");

            Integer tableId0keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Employee_Id", "Employee_Id", true, 1, 0, 0, 1);

            Integer tableId1keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Project_Id", "Project_Id", true, 1, 0, 0, 1);

            Integer tableId2keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId2, "Project_Id", "Project_Id", true, 1, 0, 0, 1);
            Integer tableId2keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId2, "Employee_Id", "Employee_Id", true, 1, 0, 0, 2);
            Integer tableId2keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId2, "Date_Started", "Date_Started", false, 6, 0, 0, 3);

            //employee -> project_employment, identifying -> 0
            Integer relationshipId0 = loadDatabase.Relationship_Insert(schemaId, tableId0, tableId2, 0, "", "is assigned to", "");
            //project -> project_employment, identifying -> 0
            Integer relationshipId1 = loadDatabase.Relationship_Insert(schemaId, tableId1, tableId2, 0, "", "has as project members", "");

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
        LoadProject loadProject = new LoadProject();
    }
}
