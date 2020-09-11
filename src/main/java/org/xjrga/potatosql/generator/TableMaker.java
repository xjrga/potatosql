package org.xjrga.potatosql.generator;

import org.xjrga.potatosql.data.DbLink;
import org.xjrga.potatosql.dataobject.DatabaseSchemaDataObject;
import org.xjrga.potatosql.dataobject.DatabaseTableDataObject;
import org.xjrga.potatosql.dataobject.TableKeyKeyTypeDataObject;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

public class TableMaker {
    private final DbLink dbLink;
    private Table table;

    public TableMaker(DbLink dbLink) {
        this.dbLink = dbLink;
    }

    public Table getTable(Integer schemaid, Integer tableid) {
        DatabaseSchemaDataObject databaseSchemaDataObject = new DatabaseSchemaDataObject();
        databaseSchemaDataObject.setSchemaId(schemaid);
        DatabaseTableDataObject databaseTableDataObject = new DatabaseTableDataObject();
        databaseTableDataObject.setSchemaId(schemaid);
        databaseTableDataObject.setTableId(tableid);
        LinkedList list = null;
        try {
            list = (LinkedList) dbLink.DatabaseTable_Select(databaseSchemaDataObject);
            Iterator it = list.iterator();
            table = new Table(((DatabaseTableDataObject) it.next()).getName());
            list = (LinkedList) dbLink.TableKey_KeyType_Select(databaseTableDataObject);
            it = list.iterator();
            while (it.hasNext()) {
                TableKeyKeyTypeDataObject tableKeyKeyTypeDataObject = (TableKeyKeyTypeDataObject) it.next();
                Column column = new Column(tableKeyKeyTypeDataObject.getKeyName());
                table.addColumn(column);
                column.setName(tableKeyKeyTypeDataObject.getKeyName());
                column.setLabel(tableKeyKeyTypeDataObject.getLabel());
                column.setPrimaryKey(tableKeyKeyTypeDataObject.getIsPK());
                column.setTypeId(tableKeyKeyTypeDataObject.getTypeId());
                column.setTypeName(tableKeyKeyTypeDataObject.getTypeName());
                column.setIdentity(tableKeyKeyTypeDataObject.getIdentity());
                column.setPrecisionRequired(tableKeyKeyTypeDataObject.getPrecisionRequired());
                column.setPrecision(tableKeyKeyTypeDataObject.getPrcsn());
                column.setScaleRequired(tableKeyKeyTypeDataObject.getScaleRequired());
                column.setScale(tableKeyKeyTypeDataObject.getScale());
                column.setOrder(tableKeyKeyTypeDataObject.getOrden());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return table;
    }
}
