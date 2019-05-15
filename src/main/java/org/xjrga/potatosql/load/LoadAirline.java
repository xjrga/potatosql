package org.xjrga.potatosql.load;

public class LoadAirline
{
    public LoadAirline()
    {

        LoadDatabase loadDatabase = new LoadDatabase();

        try
        {
            loadDatabase.turnOffAutocommit();

            Integer schemaId = loadDatabase.Schema_Insert("Airline");
            Integer tableId0 = loadDatabase.Table_Insert(schemaId, "Passenger");
            Integer tableId1 = loadDatabase.Table_Insert(schemaId, "Seat_Reservation");
            Integer tableId2 = loadDatabase.Table_Insert(schemaId, "Flight");

            Integer tableId0keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Passenger_Id", "Passenger_Id", true, 1, 0, 0, 1);
            Integer tableId0keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Passenger_Name", "Passenger_Name", false, 3, 8000, 0, 2);

            Integer tableId1keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Flight_Number", "Flight_Number", true, 1, 0, 0, 1);
            Integer tableId1keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Flight_Date", "Flight_Date", true, 6, 0, 0, 2);
            Integer tableId1keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Seat_Number", "Seat_Number", true, 1, 0, 0, 3);
            Integer tableId1keyId3 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Passenger_Id", "Passenger_Id", false, 1, 0, 0, 4);

            Integer tableId2keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId2, "Flight_Number", "Flight_Number", true, 1, 0, 0, 1);
            Integer tableId2keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId2, "Flight_Date", "Flight_Date", true, 6, 0, 0, 2);

            //passenger -> seat_reservation, non-identifying -> 1
            Integer relationshipId0 = loadDatabase.Relationship_Insert(schemaId, tableId0, tableId1, 1, "", "", "");
            //flight -> seat_reservation, non-identifying -> 0
            Integer relationshipId1 = loadDatabase.Relationship_Insert(schemaId, tableId2, tableId1, 0, "", "", "");

            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId0, tableId1, relationshipId0, tableId0keyId0, tableId1keyId3);
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId2, tableId1, relationshipId1, tableId2keyId0, tableId1keyId0);
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId2, tableId1, relationshipId1, tableId2keyId1, tableId1keyId1);

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
        LoadAirline loadAirline = new LoadAirline();
    }
}
