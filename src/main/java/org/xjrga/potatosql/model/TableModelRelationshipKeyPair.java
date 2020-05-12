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

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class TableModelRelationshipKeyPair extends DefaultTableModel {

    private Vector columns;
    private final DbLink dbLink;


    public TableModelRelationshipKeyPair(DbLink dbLink) {

        this.setColumnIdentifiers();
        this.dbLink = dbLink;
    }


    private void setColumnIdentifiers() {

        columns = new Vector();
        /*
                SchemaId,
                Parent_TableId,
                Child_TableId,
                RelationshipTypeId,
                RelationshipId,
                Parent_KeyId,
                Child_KeyId,
                b.Name as Parent,
                c.Name as Child
         */
        columns.add("Parent_KeyId");
        columns.add("Child_KeyId");
        columns.add("Parent");
        columns.add("Child");

        this.setColumnIdentifiers(columns);
    }


    public Class getColumnClass(int i) {

        Class returnValue = Object.class;
        /*
                SchemaId,
                Parent_TableId,
                Child_TableId,
                RelationshipTypeId,
                RelationshipId,
                Parent_KeyId,
                Child_KeyId,
                b.Name as Parent,
                c.Name as Child
         */
        switch (i) {

            case 0:
                //SchemaId
            case 1:
                //Parent_TableId
            case 2:
                //Child_TableId
            case 3:
                //RelationshipTypeId
            case 4:
                //RelationshipId
            case 5:
                //Parent_KeyId
            case 6:
                //Child_KeyId
                returnValue = Integer.class;
                break;
            case 7:
                //Parent
            case 8:
                //Child
                returnValue = String.class;
                break;
        }

        return returnValue;
    }


    @Override
    public boolean isCellEditable(int i, int i1) {

        return false;
    }


    public void reload(Integer schemaid, Integer parent_tableid, Integer child_tableid, Integer relationshipid) {

        Vector table = new Vector();

        LinkedList list = (LinkedList) dbLink.RelationshipKeyPair_Multiple_Select(schemaid, parent_tableid, child_tableid, relationshipid);

        Iterator it = list.iterator();

        while (it.hasNext()) {

            HashMap row_hashmap = (HashMap) it.next();
            /*
                SchemaId,
                Parent_TableId,
                Child_TableId,
                RelationshipTypeId,
                RelationshipId,
                Parent_KeyId,
                Child_KeyId,
                b.Name as Parent,
                c.Name as Child
            */
            schemaid = (Integer) row_hashmap.get("SCHEMAID");
            parent_tableid = (Integer) row_hashmap.get("PARENT_TABLEID");
            child_tableid = (Integer) row_hashmap.get("CHILD_TABLEID");
            relationshipid = (Integer) row_hashmap.get("RELATIONSHIPID");
            Integer parent_keyid = (Integer) row_hashmap.get("PARENT_KEYID");
            Integer child_keyid = (Integer) row_hashmap.get("CHILD_KEYID");
            String parent_name = (String) row_hashmap.get("PARENT");
            String child_name = (String) row_hashmap.get("CHILD");

            Vector row_vector = new Vector();

            //row_vector.add(schemaid);
            //row_vector.add(parent_tableid);
            //row_vector.add(child_tableid);
            //row_vector.add(relationshipid);
            row_vector.add(parent_keyid);
            row_vector.add(child_keyid);
            row_vector.add(parent_name);
            row_vector.add(child_name);

            table.add(row_vector);
        }

        this.setDataVector(table, columns);

    }


    public void clear() {

        Vector table = new Vector();
        this.setDataVector(table, columns);

    }

}
