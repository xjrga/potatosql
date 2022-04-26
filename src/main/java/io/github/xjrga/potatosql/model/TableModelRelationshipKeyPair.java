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
package io.github.xjrga.potatosql.model;

import io.github.xjrga.potatosql.data.Dblink;
import io.github.xjrga.potatosql.data.dto.O_key_pair_multiple_select;
import io.github.xjrga.potatosql.data.dto.O_relationship;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class TableModelRelationshipKeyPair extends DefaultTableModel {

    private Vector columns;
    private Dblink dbLink;

    public TableModelRelationshipKeyPair(Dblink dbLink) {

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

    public void reload(O_relationship o) {

        Vector table = new Vector();

        //LinkedList list = (LinkedList) dbLink.RelationshipKeyPair_Multiple_Select(schemaid, parent_tableid, child_tableid, relationshipid);
        List<O_key_pair_multiple_select> list = (List<O_key_pair_multiple_select>) dbLink.relationship_key_pair_multiple_select(o);

        Iterator<O_key_pair_multiple_select> it = list.iterator();

        while (it.hasNext()) {

            O_key_pair_multiple_select next = it.next();
            Integer schemaid = next.getSchema_id();
            Integer parent_tableid = next.getParent_table_id();
            Integer child_tableid = next.getChild_table_id();
            Integer relationshipid = next.getRelationship_id();
            Integer parent_keyid = next.getParent_key_id();
            Integer child_keyid = next.getChild_key_id();
            String parent_name = next.getParent();
            String child_name = next.getChild();

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
