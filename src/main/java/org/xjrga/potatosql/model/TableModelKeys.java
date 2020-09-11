/*
 * Snack: Nutritional Software
 * Copyright (C) 2018 Jorge R Garcia de Alba
 * License: http://www.gnu.org/licenses/gpl.html GPL version 2 or higher
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.xjrga.potatosql.model;

import org.xjrga.potatosql.data.DbLink;
import org.xjrga.potatosql.dataobject.DatabaseTableDataObject;
import org.xjrga.potatosql.dataobject.TableKeyKeyTypeDataObject;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class TableModelKeys extends DefaultTableModel {
    private final DbLink dbLink;
    private Vector columns;

    public TableModelKeys(DbLink dbLink) {
        this.setColumnIdentifiers();
        this.dbLink = dbLink;
    }

    private void setColumnIdentifiers() {
        columns = new Vector();
        columns.add("SchemaId");
        columns.add("TableId");
        columns.add("KeyId");
        columns.add("Name");
        columns.add("Label");
        columns.add("Primary Key");
        columns.add("TypeId");
        columns.add("Type");
        columns.add("Precision");
        columns.add("Scale");
        columns.add("Order");
        this.setColumnIdentifiers(columns);
    }

    public Class getColumnClass(int i) {
        Class returnValue = Object.class;
        switch (i) {
            case 0:
                //SchemaId
            case 1:
                //TableId
            case 2:
                //KeyId
                returnValue = Integer.class;
                break;
            case 3:
                //Name
            case 4:
                //Label
                returnValue = String.class;
                break;
            case 5:
                //IsPK
                returnValue = Boolean.class;
                break;
            case 6:
                //TypeId
                returnValue = Integer.class;
                break;
            case 7:
                //TypeName
                returnValue = String.class;
                break;
            case 8:
                //Precision
            case 9:
                //Scale
            case 10:
                //Order
                returnValue = Integer.class;
                break;
        }
        return returnValue;
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    public void reload(DatabaseTableDataObject databaseTableDataObject) {
        Vector table = new Vector();
        LinkedList list = null;
        try {
            list = (LinkedList) dbLink.TableKey_KeyType_Select(databaseTableDataObject);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                TableKeyKeyTypeDataObject tableKeyKeyTypeDataObject = (TableKeyKeyTypeDataObject) it.next();
                Vector row_vector = new Vector();
                row_vector.add(tableKeyKeyTypeDataObject.getSchemaId());
                row_vector.add(tableKeyKeyTypeDataObject.getTableId());
                row_vector.add(tableKeyKeyTypeDataObject.getKeyId());
                row_vector.add(tableKeyKeyTypeDataObject.getKeyName());
                row_vector.add(tableKeyKeyTypeDataObject.getLabel());
                row_vector.add(tableKeyKeyTypeDataObject.getIsPK());
                row_vector.add(tableKeyKeyTypeDataObject.getTypeId());
                row_vector.add(tableKeyKeyTypeDataObject.getTypeName());
                row_vector.add(tableKeyKeyTypeDataObject.getPrcsn());
                row_vector.add(tableKeyKeyTypeDataObject.getScale());
                row_vector.add(tableKeyKeyTypeDataObject.getOrden());
                table.add(row_vector);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.setDataVector(table, columns);
    }

    public void clear() {
        Vector table = new Vector();
        this.setDataVector(table, columns);
    }
}
