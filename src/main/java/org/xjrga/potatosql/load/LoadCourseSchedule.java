package org.xjrga.potatosql.load;

public class LoadCourseSchedule
{
    public LoadCourseSchedule()
    {

        LoadDatabase loadDatabase = new LoadDatabase();

        try
        {
            loadDatabase.turnOffAutocommit();

            Integer schemaId = loadDatabase.Schema_Insert("ClassSchedule");
            Integer tableId0 = loadDatabase.Table_Insert(schemaId, "Course");
            Integer tableId1 = loadDatabase.Table_Insert(schemaId, "Registration");
            Integer tableId2 = loadDatabase.Table_Insert(schemaId, "Student");
            Integer tableId3 = loadDatabase.Table_Insert(schemaId, "Teacher");
            Integer tableId4 = loadDatabase.Table_Insert(schemaId, "Program");
            Integer tableId5 = loadDatabase.Table_Insert(schemaId, "TeacherLevel");

            //Course
            Integer tableId0keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId0, "CourseId", "CourseId", true, 1, 0, 0, 1);
            Integer tableId0keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId0, "TeacherId", "TeacherId", false, 1, 0, 0, 2);
            Integer tableId0keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId0, "ProgramId", "ProgramId", false, 1, 0, 0, 3);
            Integer tableId0keyId3 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Name", "Name", false, 3, 8000, 0, 4);
            Integer tableId0keyId4 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Required", "Required", false, 4, 0, 0, 5);

            //Registration
            Integer tableId1keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId1, "CourseId", "CourseId", true, 1, 0, 0, 2);
            Integer tableId1keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId1, "StudentId", "StudentId", true, 1, 0, 0, 3);

            //Student
            Integer tableId2keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId2, "StudentId", "StudentId", true, 1, 0, 0, 1);
            Integer tableId2keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId2, "ProgramId", "ProgramId", false, 1, 0, 0, 2);
            Integer tableId2keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId2, "Name", "Name", false, 3, 8000, 0, 3);

            //Teacher
            Integer tableId3keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId3, "TeacherId", "TeacherId", true, 1, 0, 0, 1);
            Integer tableId3keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId3, "LevelCode", "LevelCode", false, 1, 0, 0, 2);
            Integer tableId3keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId3, "Name", "Name", false, 3, 8000, 0, 3);

            //Program
            Integer tableId4keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId4, "ProgramId", "ProgramId", true, 1, 0, 0, 1);
            Integer tableId4keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId4, "Name", "Name", false, 3, 8000, 0, 2);

            //Teacher Level
            Integer tableId5keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId5, "LevelCode", "LevelCode", true, 1, 0, 0, 1);
            Integer tableId5keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId5, "Title", "Title", false, 3, 8000, 0, 2);

            //Course -> Registration, identifying
            Integer relationshipId0 = loadDatabase.Relationship_Insert(schemaId, tableId0, tableId1, 0, "", "", "");

            //Student -> Registration, identifying
            Integer relationshipId1 = loadDatabase.Relationship_Insert(schemaId, tableId2, tableId1, 0, "", "", "");

            //Program -> Course, non-identifying
            Integer relationshipId2 = loadDatabase.Relationship_Insert(schemaId, tableId4, tableId0, 1, "", "", "");

            //Program -> Student, non-identifying
            Integer relationshipId3 = loadDatabase.Relationship_Insert(schemaId, tableId4, tableId2, 1, "", "", "");

            //Teacher -> Course, non-identifying
            Integer relationshipId4 = loadDatabase.Relationship_Insert(schemaId, tableId3, tableId0, 1, "", "", "");

            //Teacher Level -> Teacher, non-identifying
            Integer relationshipId5 = loadDatabase.Relationship_Insert(schemaId, tableId5, tableId3, 1, "", "", "");

            //Course -> Registration, courseid,courseid
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId0, tableId1, relationshipId0, tableId0keyId0, tableId1keyId0);

            //Student -> Registration, studentid, studentid
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId2, tableId1, relationshipId1, tableId2keyId0, tableId1keyId1);

            //Program -> Course, programid, programid
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId4, tableId0, relationshipId2, tableId4keyId0, tableId0keyId2);

            //Program -> Student, programid, programid
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId4, tableId2, relationshipId3, tableId4keyId0, tableId2keyId1);

            //Teacher -> Course, teacherid, teacherid
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId3, tableId0, relationshipId4, tableId3keyId0, tableId0keyId1);

            //Teacher Level -> Teacher, levelcode, levelcode
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId5, tableId3, relationshipId5, tableId5keyId0, tableId3keyId1);

            loadDatabase.turnOnAutocommit();
        }
        catch (Exception e)
        {
            loadDatabase.rollback();
        }
        finally
        {
            //loadDatabase.close();
            loadDatabase.shutdown();
        }

    }

    public static void main(String[] args)
    {
        LoadCourseSchedule loadAirline = new LoadCourseSchedule();
    }
}
