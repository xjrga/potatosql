package org.xjrga.potatosql.generator;

import org.xjrga.potatosql.data.DbLink;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class TableMaker
{

    private DbLink dbLink;
    private Table table;


    public TableMaker(DbLink dbLink)
    {

        this.dbLink = dbLink;
    }


    public Table getTable(Integer schemaid, Integer tableid)
    {

        LinkedList list = (LinkedList) dbLink.DatabaseTable_Select(schemaid, tableid);
        Iterator it = list.iterator();
        HashMap row = (HashMap) it.next();
        table = new Table((String) row.get("NAME"));
        list = (LinkedList) dbLink.TableKey_KeyType_Select(schemaid, tableid);
        it = list.iterator();

        while (it.hasNext())
        {

            row = (HashMap) it.next();
            schemaid = (Integer) row.get("SCHEMAID");
            tableid = (Integer) row.get("TABLEID");
            Integer keyid = (Integer) row.get("KEYID");
            String name = (String) row.get("NAME");
            String label = (String) row.get("LABEL");
            Boolean ispk = (Boolean) row.get("ISPK");
            Integer typeid = (Integer) row.get("TYPEID");
            String typename = (String) row.get("TYPENAME");
            Boolean isidentity = (Boolean) row.get("ISIDENTITY");
            Boolean precisionrequired = (Boolean) row.get("PRECISIONREQUIRED");
            Integer precision = (Integer) row.get("PRECISION");
            Integer order = (Integer) row.get("ORDEN");

            Column column = new Column(name);
            table.addColumn(column);

            column.setName(name);
            column.setLabel(label);
            column.setPrimaryKey(ispk);
            column.setTypeId(typeid);
            column.setTypeName(typename);
            column.setIdentity(isidentity);
            column.setPrecisionRequired(precisionrequired);
            column.setPrecision(precision);
            column.setOrder(order);

        }
        return table;
    }

}
