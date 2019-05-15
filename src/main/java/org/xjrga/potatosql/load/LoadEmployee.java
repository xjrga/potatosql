package org.xjrga.potatosql.load;

public class LoadEmployee
{
    public LoadEmployee()
    {

        LoadDatabase loadDatabase = new LoadDatabase();
        //loadDatabase.setTransactionIsolation();
        loadDatabase.turnOffAutocommit();

        try
        {


            //schema
            Integer schemaId = loadDatabase.Schema_Insert("Employee");
            //tables
            Integer tableId0 = loadDatabase.Table_Insert(schemaId, "employees");
            Integer tableId1 = loadDatabase.Table_Insert(schemaId, "departments");
            Integer tableId2 = loadDatabase.Table_Insert(schemaId, "dept_manager");
            Integer tableId3 = loadDatabase.Table_Insert(schemaId, "dept_emp");
            Integer tableId4 = loadDatabase.Table_Insert(schemaId, "titles");
            Integer tableId5 = loadDatabase.Table_Insert(schemaId, "salaries");

            //employees
            Integer tableId0keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId0, "emp_no", "emp_no", true, 1, 0, 0, 1);
            Integer tableId0keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId0, "birth_date", "birth_date", false, 6, 0, 0, 2);
            Integer tableId0keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId0, "first_name", "first_name", false, 3, 14, 0, 3);
            Integer tableId0keyId3 = loadDatabase.TableKey_Insert(schemaId, tableId0, "last_name", "last_name", false, 3, 16, 0, 4);
            Integer tableId0keyId4 = loadDatabase.TableKey_Insert(schemaId, tableId0, "gender", "gender", false, 1, 3, 1, 5);
            Integer tableId0keyId5 = loadDatabase.TableKey_Insert(schemaId, tableId0, "hire_date", "hire_date", false, 6, 0, 0, 6);

            //departments
            Integer tableId1keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId1, "dept_no", "dept_no", true, 3, 4, 0, 1);
            Integer tableId1keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId1, "dept_name", "dept_name", false, 6, 40, 0, 2);

            //dept_manager
            Integer tableId2keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId2, "emp_no", "emp_no", true, 1, 0, 0, 1);
            Integer tableId2keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId2, "dept_no", "dept_no", true, 3, 4, 0, 2);
            Integer tableId2keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId2, "from_date", "from_date", false, 6, 0, 0, 3);
            Integer tableId2keyId3 = loadDatabase.TableKey_Insert(schemaId, tableId2, "to_date", "to_date", false, 6, 0, 0, 4);

            //dept_emp
            Integer tableId3keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId3, "emp_no", "emp_no", true, 1, 0, 0, 1);
            Integer tableId3keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId3, "dept_no", "dept_no", true, 3, 4, 0, 2);
            Integer tableId3keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId3, "from_date", "from_date", false, 6, 0, 0, 3);
            Integer tableId3keyId3 = loadDatabase.TableKey_Insert(schemaId, tableId3, "to_date", "to_date", false, 6, 0, 0, 4);

            //titles
            Integer tableId4keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId4, "emp_no", "emp_no", true, 1, 0, 0, 1);
            Integer tableId4keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId4, "title", "title", true, 3, 50, 0, 2);
            Integer tableId4keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId4, "from_date", "from_date", true, 6, 0, 0, 3);
            Integer tableId4keyId3 = loadDatabase.TableKey_Insert(schemaId, tableId4, "to_date", "to_date", false, 6, 0, 0, 4);

            //salaries
            Integer tableId5keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId5, "emp_no", "emp_no", true, 1, 0, 0, 1);
            Integer tableId5keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId5, "salary", "salary", false, 1, 0, 0, 2);
            Integer tableId5keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId5, "from_date", "from_date", true, 6, 0, 0, 3);
            Integer tableId5keyId3 = loadDatabase.TableKey_Insert(schemaId, tableId5, "to_date", "to_date", false, 6, 0, 0, 4);

            //employees -> dept_manager, identifying -> 0
            Integer relationshipId0 = loadDatabase.Relationship_Insert(schemaId, tableId0, tableId2, 0, "", "", "");
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId0, tableId2, relationshipId0, tableId0keyId0, tableId2keyId0);
            //departments -> dept_manager, identifying -> 0
            Integer relationshipId1 = loadDatabase.Relationship_Insert(schemaId, tableId1, tableId2, 0, "", "", "");
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId1, tableId2, relationshipId1, tableId1keyId0, tableId2keyId1);
            //employees -> dept_emp, identifying -> 0
            Integer relationshipId2 = loadDatabase.Relationship_Insert(schemaId, tableId0, tableId3, 0, "", "", "");
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId0, tableId3, relationshipId2, tableId0keyId0, tableId3keyId0);
            //departments -> dept_emp, identifying -> 0
            Integer relationshipId3 = loadDatabase.Relationship_Insert(schemaId, tableId1, tableId3, 0, "", "", "");
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId1, tableId3, relationshipId3, tableId1keyId0, tableId3keyId1);
            //employees -> titles, identifying -> 0
            Integer relationshipId4 = loadDatabase.Relationship_Insert(schemaId, tableId0, tableId4, 0, "", "", "");
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId0, tableId4, relationshipId4, tableId0keyId0, tableId4keyId0);
            //employees -> salaries, identifying -> 0
            Integer relationshipId5 = loadDatabase.Relationship_Insert(schemaId, tableId0, tableId5, 0, "", "", "");
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId0, tableId5, relationshipId5 + 9, tableId0keyId0, tableId5keyId0);

            loadDatabase.turnOnAutocommit();
        }
        catch (Exception e)
        {
            loadDatabase.rollback();
        }
        finally
        {
            //loadDatabase.commit();
            loadDatabase.shutdown();
        }

    }


    public static void main(String[] args)
    {
        LoadEmployee loadProject = new LoadEmployee();
    }
}
