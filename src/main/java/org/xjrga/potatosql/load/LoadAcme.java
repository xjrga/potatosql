package org.xjrga.potatosql.load;

public class LoadAcme
{
    public LoadAcme()
    {

        LoadDatabase loadDatabase = new LoadDatabase();

        try
        {
            loadDatabase.turnOffAutocommit();

            Integer schemaId = loadDatabase.Schema_Insert("Acme");
            Integer tableId0 = loadDatabase.Table_Insert(schemaId, "Offices");
            Integer tableId1 = loadDatabase.Table_Insert(schemaId, "Employees");
            Integer tableId2 = loadDatabase.Table_Insert(schemaId, "Customers");
            Integer tableId3 = loadDatabase.Table_Insert(schemaId, "Payments");
            Integer tableId4 = loadDatabase.Table_Insert(schemaId, "Orders");
            Integer tableId5 = loadDatabase.Table_Insert(schemaId, "OrderDetails");
            Integer tableId6 = loadDatabase.Table_Insert(schemaId, "Products");
            Integer tableId7 = loadDatabase.Table_Insert(schemaId, "ProductLines");

            //Offices
            Integer tableId0keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId0, "OfficeCode", "OfficeCode", true, 1, 0, 0, 1);
            Integer tableId0keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId0, "City", "City", false, 3, 8000, 0, 2);
            Integer tableId0keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Phone", "Phone", false, 3, 8000, 0, 3);
            Integer tableId0keyId3 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Address", "Address", false, 3, 8000, 0, 4);
            Integer tableId0keyId4 = loadDatabase.TableKey_Insert(schemaId, tableId0, "State", "State", false, 3, 8000, 0, 5);
            Integer tableId0keyId5 = loadDatabase.TableKey_Insert(schemaId, tableId0, "Country", "Country", false, 3, 8000, 0, 6);
            Integer tableId0keyId6 = loadDatabase.TableKey_Insert(schemaId, tableId0, "PostalCode", "PostalCode", false, 3, 8000, 0, 7);

            //Employees
            Integer tableId1keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId1, "EmployeeNumber", "EmployeeNumber", true, 1, 0, 0, 1);
            Integer tableId1keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId1, "LastName", "LastName", false, 3, 8000, 0, 2);
            Integer tableId1keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId1, "FirstName", "FirstName", false, 3, 8000, 0, 3);
            Integer tableId1keyId3 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Extension", "Extension", false, 1, 0, 0, 4);
            Integer tableId1keyId4 = loadDatabase.TableKey_Insert(schemaId, tableId1, "Email", "Email", false, 3, 8000, 0, 5);
            Integer tableId1keyId5 = loadDatabase.TableKey_Insert(schemaId, tableId1, "OfficeCode", "OfficeCode", false, 1, 0, 0, 6);
            Integer tableId1keyId6 = loadDatabase.TableKey_Insert(schemaId, tableId1, "ReportsTo", "ReportsTo", false, 1, 0, 0, 7);
            Integer tableId1keyId7 = loadDatabase.TableKey_Insert(schemaId, tableId1, "JobTitle", "JobTitle", false, 3, 8000, 0, 8);

            //Customers
            Integer tableId2keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId2, "CustomerNumber", "CustomerNumber", true, 1, 0, 0, 1);
            Integer tableId2keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId2, "CustomerName", "CustomerName", false, 3, 8000, 0, 2);
            Integer tableId2keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId2, "Phone", "Phone", false, 3, 8000, 0, 3);
            Integer tableId2keyId3 = loadDatabase.TableKey_Insert(schemaId, tableId2, "Address", "Address", false, 3, 8000, 0, 4);
            Integer tableId2keyId4 = loadDatabase.TableKey_Insert(schemaId, tableId2, "City", "City", false, 3, 8000, 0, 5);
            Integer tableId2keyId5 = loadDatabase.TableKey_Insert(schemaId, tableId2, "State", "State", false, 3, 8000, 0, 6);
            Integer tableId2keyId6 = loadDatabase.TableKey_Insert(schemaId, tableId2, "PostalCode", "PostalCode", false, 3, 8000, 0, 7);
            Integer tableId2keyId7 = loadDatabase.TableKey_Insert(schemaId, tableId2, "Country", "Country", false, 3, 8000, 0, 8);
            Integer tableId2keyId8 = loadDatabase.TableKey_Insert(schemaId, tableId2, "SalesRepEmployeeNumber", "SalesRepEmployeeNumber", false, 1, 0, 0, 9);
            Integer tableId2keyId9 = loadDatabase.TableKey_Insert(schemaId, tableId2, "CreditLimit", "CreditLimit", false, 1, 8000, 0, 10);

            //Payments
            Integer tableId3keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId3, "CustomerNumber", "CustomerNumber", true, 1, 0, 0, 1);
            Integer tableId3keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId3, "CheckNumber", "CheckNumber", false, 1, 0, 0, 2);
            Integer tableId3keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId3, "PaymentDate", "PaymentDate", false, 6, 0, 0, 3);
            Integer tableId3keyId3 = loadDatabase.TableKey_Insert(schemaId, tableId3, "Amount", "Amount", false, 2, 0, 0, 4);

            //Orders
            Integer tableId4keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId4, "OrderNumber", "OrderNumber", true, 1, 0, 0, 1);
            Integer tableId4keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId4, "OrderDate", "OrderDate", false, 6, 0, 0, 2);
            Integer tableId4keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId4, "RequiredDate", "RequiredDate", false, 6, 0, 0, 3);
            Integer tableId4keyId3 = loadDatabase.TableKey_Insert(schemaId, tableId4, "ShippedDate", "ShippedDate", false, 6, 0, 0, 4);
            Integer tableId4keyId4 = loadDatabase.TableKey_Insert(schemaId, tableId4, "Status", "Status", false, 3, 8000, 0, 5);
            Integer tableId4keyId5 = loadDatabase.TableKey_Insert(schemaId, tableId4, "Comments", "Comments", false, 3, 8000, 0, 6);
            Integer tableId4keyId6 = loadDatabase.TableKey_Insert(schemaId, tableId4, "CustomerNumber", "CustomerNumber", false, 1, 0, 0, 7);

            //Order Details
            Integer tableId5keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId5, "OrderNumber", "OrderNumber", true, 1, 0, 0, 1);
            Integer tableId5keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId5, "ProductCode", "ProductCode", true, 1, 0, 0, 2);
            Integer tableId5keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId5, "QuantityOrdered", "QuantityOrdered", false, 1, 0, 0, 3);
            Integer tableId5keyId3 = loadDatabase.TableKey_Insert(schemaId, tableId5, "PriceEach", "PriceEach", false, 2, 0, 0, 4);
            Integer tableId5keyId4 = loadDatabase.TableKey_Insert(schemaId, tableId5, "OrderLineNumber", "OrderLineNumber", false, 1, 0, 0, 5);

            //Products
            Integer tableId6keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId6, "ProductCode", "ProductCode", true, 1, 0, 0, 1);
            Integer tableId6keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId6, "ProductName", "ProductName", false, 3, 8000, 0, 2);
            Integer tableId6keyId2 = loadDatabase.TableKey_Insert(schemaId, tableId6, "ProductLine", "ProductLine", false, 1, 0, 0, 3);
            Integer tableId6keyId3 = loadDatabase.TableKey_Insert(schemaId, tableId6, "ProductScale", "ProductScale", false, 3, 8000, 0, 4);
            Integer tableId6keyId4 = loadDatabase.TableKey_Insert(schemaId, tableId6, "ProductVendor", "ProductVendor", false, 3, 8000, 0, 5);
            Integer tableId6keyId5 = loadDatabase.TableKey_Insert(schemaId, tableId6, "ProductDescription", "ProductDescription", false, 3, 8000, 0, 6);
            Integer tableId6keyId6 = loadDatabase.TableKey_Insert(schemaId, tableId6, "QuantityInStock", "QuantityInStock", false, 1, 0, 0, 7);
            Integer tableId6keyId7 = loadDatabase.TableKey_Insert(schemaId, tableId6, "BuyPrice", "BuyPrice", false, 2, 0, 0, 8);
            Integer tableId6keyId8 = loadDatabase.TableKey_Insert(schemaId, tableId6, "MSRP", "MSRP", false, 2, 0, 0, 9);

            //Product Lines
            Integer tableId7keyId0 = loadDatabase.TableKey_Insert(schemaId, tableId7, "ProductLine", "ProductLine", true, 1, 0, 0, 1);
            Integer tableId7keyId1 = loadDatabase.TableKey_Insert(schemaId, tableId7, "ProductLineName", "ProductLineName", false, 3, 8000, 0, 2);

            //offices -> employees, non-identifying
            Integer relationshipId0 = loadDatabase.Relationship_Insert(schemaId, tableId0, tableId1, 1, "", "", "");

            //employees -> employees, non-identifying
            Integer relationshipId1 = loadDatabase.Relationship_Insert(schemaId, tableId1, tableId1, 1, "", "", "");

            //employees -> customers, non-identifying
            Integer relationshipId2 = loadDatabase.Relationship_Insert(schemaId, tableId1, tableId2, 1, "", "", "");

            //customers -> payments, identifying
            Integer relationshipId3 = loadDatabase.Relationship_Insert(schemaId, tableId2, tableId3, 0, "", "", "");

            //customers -> orders, non-identifying
            Integer relationshipId4 = loadDatabase.Relationship_Insert(schemaId, tableId2, tableId4, 1, "", "", "");

            //orders -> order details, identifying
            Integer relationshipId5 = loadDatabase.Relationship_Insert(schemaId, tableId4, tableId5, 0, "", "", "");

            //products -> order details, identifying
            Integer relationshipId6 = loadDatabase.Relationship_Insert(schemaId, tableId6, tableId5, 0, "", "", "");

            //product lines -> products, non-identifying
            Integer relationshipId7 = loadDatabase.Relationship_Insert(schemaId, tableId7, tableId6, 1, "", "", "");

            //offices -> employees, officecode, officecode
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId0, tableId1, relationshipId0, tableId0keyId0, tableId1keyId5);

            //employees -> employees, employeenumber, reportsto
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId1, tableId1, relationshipId1, tableId1keyId0, tableId1keyId6);

            //employees -> customers, employeenumber,salesrepemployeenumber
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId1, tableId2, relationshipId2, tableId1keyId0, tableId2keyId8);

            //customers -> payments, customernumber,customernumber
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId2, tableId3, relationshipId3, tableId2keyId0, tableId3keyId0);

            //customers -> orders, customernumber, customernumber
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId2, tableId4, relationshipId4, tableId2keyId0, tableId4keyId6);

            //orders -> orderdetails, ordernumber, ordernumber
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId4, tableId5, relationshipId5, tableId4keyId0, tableId5keyId0);

            //products -> orderdetails, productcode, productcode
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId6, tableId5, relationshipId6, tableId6keyId0, tableId5keyId1);

            //productlines -> products, productline, productline
            loadDatabase.RelationshipKeyPair_Insert(schemaId, tableId7, tableId6, relationshipId7, tableId7keyId0, tableId6keyId2);

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
        LoadAcme loadAirline = new LoadAcme();
    }
}
