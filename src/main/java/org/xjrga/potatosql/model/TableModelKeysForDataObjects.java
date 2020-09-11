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
import org.xjrga.potatosql.dataobject.RelationshipDataObjectExtra;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class TableModelKeysForDataObjects extends DefaultTableModel {
    private final DbLink dbLink;
    private Vector columns;

    public TableModelKeysForDataObjects(DbLink dbLink) {
        this.setColumnIdentifiers();
        this.dbLink = dbLink;
    }

    private void setColumnIdentifiers() {
        columns = new Vector();
        columns.add("SchemaId");
        columns.add("Parent_TableId");
        columns.add("Parent");
        columns.add("Child_TableId");
        columns.add("Child");
        columns.add("RelationshipTypeId");
        columns.add("RelationshipId");
        this.setColumnIdentifiers(columns);
    }

    public Class getColumnClass(int i) {
        Class returnValue = Object.class;
        switch (i) {
            case 0:
                //SchemaId
            case 1:
                //Parent_TableId
                returnValue = Integer.class;
                break;
            case 2:
                //Parent
                returnValue = String.class;
                break;
            case 3:
                //Child_TableId
                returnValue = Integer.class;
                break;
            case 4:
                //Child
                returnValue = String.class;
                break;
            case 5:
                //RelationshipTypeId
                returnValue = Integer.class;
                break;
            case 6:
                //RelationshipId
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
            list = (LinkedList) dbLink.Relationship_Multiple_Select(databaseTableDataObject);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                RelationshipDataObjectExtra next = (RelationshipDataObjectExtra) it.next();
                Vector row_vector = new Vector();
                row_vector.add(next.getRelationshipId());
                row_vector.add(next.getSchemaId());
                row_vector.add(next.getParent_TableId());
                row_vector.add(next.getParentName());
                row_vector.add(next.getChild_TableId());
                row_vector.add(next.getChildName());
                row_vector.add(next.getRelationshipTypeId());
                row_vector.add(next.getRelationshipTypeName());
                row_vector.add(next.getRelationshipName());
                row_vector.add(next.getForwardVerbPhrase());
                row_vector.add(next.getReverseVerbPhrase());
                table.add(row_vector);
            }
            this.setDataVector(table, columns);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void clear() {
        Vector table = new Vector();
        this.setDataVector(table, columns);
    }
}
