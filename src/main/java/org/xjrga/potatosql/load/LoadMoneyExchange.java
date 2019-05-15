package org.xjrga.potatosql.load;

public class LoadMoneyExchange
{
    public LoadMoneyExchange()
    {

        LoadDatabase loadDatabase = new LoadDatabase();

        try
        {
            loadDatabase.turnOffAutocommit();

            Integer schemaId = loadDatabase.Schema_Insert("MoneyExchange");
            Integer tableId0 = loadDatabase.Table_Insert(schemaId, "Currency");
            Integer tableId1 = loadDatabase.Table_Insert(schemaId, "Trade");

            Integer tableId0keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Currency_Id", "Currency_Id", true, 1, 0, 0, 1);
            Integer tableId0keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Currency_Name", "Currency_Name", false, 3, 8000, 0, 2);

            Integer tableId1keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Trade_Id", "Trade_Id", true, 1, 0, 0, 1);
            Integer tableId1keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Bought_Currency_Id", "Bought_Currency_Id", false, 1, 0, 0, 2);
            Integer tableId1keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Bought_Currency_Amount", "Bought_Currency_Amount", false, 2, 0, 0, 3);
            Integer tableId1keyId3 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Sold_Currency_Id", "Sold_Currency_Id", false, 1, 0, 0, 4);
            Integer tableId1keyId4 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Sold_Currency_Amount", "Sold_Currency_Amount", false, 2, 0, 0, 5);

            //currency -> trade, non-identifying -> 1
            Integer relationshipId0 = loadDatabase.Relationship_Insert(schemaId, tableId0, tableId1, 1, "", "is bought by", "");
            //currency -> trade, non-identifying -> 1
            Integer relationshipId1 = loadDatabase.Relationship_Insert(schemaId, tableId0, tableId1, 1, "", "is sold by", "");

            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId0, tableId1, relationshipId0, tableId0keyId0, tableId1keyId1);
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId0, tableId1, relationshipId1, tableId0keyId0, tableId1keyId3);

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
        LoadMoneyExchange loadMoneyExchange = new LoadMoneyExchange();
    }
}
