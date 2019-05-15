package org.xjrga.potatosql.load;

public class LoadEmployeeGroup
{
    public LoadEmployeeGroup()
    {

        LoadDatabase loadDatabase = new LoadDatabase();

        try
        {
            loadDatabase.turnOffAutocommit();

            Integer schemaId = loadDatabase.Schema_Insert("Employee");
            Integer tableId0 = loadDatabase.Table_Insert(schemaId, "Employee");

            Integer tableId0keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Employee_Id", "Employee_Id", true, 1, 0, 0, 1);
            Integer tableId0keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Employee_Name", "Employee_Name", false, 3, 8000, 0, 2);
            Integer tableId0keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Manager_Id", "Manager_Id", false, 1, 0, 0, 3);

            //employee -> employee, non-identifying -> 1
            Integer relationshipId0 = loadDatabase.Relationship_Insert(schemaId, tableId0, tableId0, 1, "", "has", "");

            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId0, tableId0, relationshipId0, tableId0keyId0, tableId0keyId2);

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
        LoadEmployee loadProject = new LoadEmployee();
    }
}
