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

public class TableModelRelationship extends DefaultTableModel
{

    private Vector columns;
    private DbLink dbLink;


    public TableModelRelationship(DbLink dbLink)
    {

        this.setColumnIdentifiers();
        this.dbLink = dbLink;
    }


    private void setColumnIdentifiers()
    {

        columns = new Vector();

        columns.add("Id");
        columns.add("SchemaId");
        columns.add("Parent_TableId");
        columns.add("Parent");
        columns.add("Child_TableId");
        columns.add("Child");
        columns.add("RelationshipTypeId");
        columns.add("Type");
        columns.add("Name");
        columns.add("ForwardVerbPhrase");
        columns.add("InverseVerbPhrase");

        this.setColumnIdentifiers(columns);
    }


    public Class getColumnClass(int i)
    {

        Class returnValue = Object.class;
        switch (i)
        {

            case 0:
                //Id
            case 1:
                //SchemaId
            case 2:
                //Parent_TableId
                returnValue = Integer.class;
                break;
            case 3:
                //Parent
                returnValue = String.class;
                break;
            case 4:
                //Child_TableId
                returnValue = Integer.class;
                break;
            case 5:
                //Child
                returnValue = String.class;
                break;
            case 6:
                //RelationshipTypeId
                returnValue = Integer.class;
                break;
            case 7:
                //Type
                returnValue = String.class;
                break;
            case 8:
                //Name
                returnValue = String.class;
                break;
            case 9:
                //ForwardVerbPhrase
                returnValue = String.class;
                break;
            case 10:
                //ReverseVerbPhrase
                returnValue = String.class;
                break;

        }
        return returnValue;
    }


    @Override
    public boolean isCellEditable(int i, int i1)
    {

        return false;
    }


    public void reload(int schemaid)
    {

        Vector table = new Vector();

        LinkedList list = (LinkedList) dbLink.Relationship_Multiple_Select(schemaid);

        Iterator it = list.iterator();

        while (it.hasNext())
        {

            HashMap row_hashmap = (HashMap) it.next();

            /*
            *   SchemaId,
                Parent_TableId,
                b.Name as Parent,
                Child_TableId,
                c.Name as Child,
                RelationshipTypeId,
                RelationshipId,
                d.Name as RelationshipTypeName,
                a.Nameas as RelationshipName,
                a.ForwardVerbPhrase,
                a.ReverseVerbPhrase
            *
            * */
            //schemaid = (Integer)row_hashmap.get("SCHEMAID");
            Integer parent_tableid = (Integer) row_hashmap.get("PARENT_TABLEID");
            String parent = (String) row_hashmap.get("PARENT");
            Integer child_tableid = (Integer) row_hashmap.get("CHILD_TABLEID");
            String child = (String) row_hashmap.get("CHILD");
            Integer relationshiptypeid = (Integer) row_hashmap.get("RELATIONSHIPTYPEID");
            Integer relationshipid = (Integer) row_hashmap.get("RELATIONSHIPID");
            String relationshiptypename = (String) row_hashmap.get("RELATIONSHIPTYPENAME");
            String relationshipname = (String) row_hashmap.get("RELATIONSHIPNAME");
            String forwardverbphrase = (String) row_hashmap.get("FORWARDVERBPHRASE");
            String reverseverbphrase = (String) row_hashmap.get("REVERSEVERBPHRASE");

            Vector row_vector = new Vector();

            row_vector.add(relationshipid);
            row_vector.add(schemaid);
            row_vector.add(parent_tableid);
            row_vector.add(parent);
            row_vector.add(child_tableid);
            row_vector.add(child);
            row_vector.add(relationshiptypeid);
            row_vector.add(relationshiptypename);
            row_vector.add(relationshipname);
            row_vector.add(forwardverbphrase);
            row_vector.add(reverseverbphrase);

            table.add(row_vector);
        }

        this.setDataVector(table, columns);

    }


    public void clear()
    {

        Vector table = new Vector();
        this.setDataVector(table, columns);

    }

}
