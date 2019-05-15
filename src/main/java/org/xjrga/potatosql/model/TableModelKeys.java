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

public class TableModelKeys extends DefaultTableModel
{

    private Vector columns;
    private DbLink dbLink;


    public TableModelKeys(DbLink dbLink)
    {

        this.setColumnIdentifiers();
        this.dbLink = dbLink;
    }


    private void setColumnIdentifiers()
    {

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


    public Class getColumnClass(int i)
    {

        Class returnValue = Object.class;
        switch (i)
        {

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
    public boolean isCellEditable(int i, int i1)
    {

        return false;
    }


    public void reload(int schemaid, int tableid)
    {

        Vector table = new Vector();

        LinkedList list = (LinkedList) dbLink.TableKey_KeyType_Select(schemaid, tableid);

        Iterator it = list.iterator();

        while (it.hasNext())
        {

            HashMap row_hashmap = (HashMap) it.next();

            schemaid = (Integer) row_hashmap.get("SCHEMAID");
            tableid = (Integer) row_hashmap.get("TABLEID");
            Integer keyid = (Integer) row_hashmap.get("KEYID");
            String name = (String) row_hashmap.get("NAME");
            String label = (String) row_hashmap.get("LABEL");
            Boolean ispk = (Boolean) row_hashmap.get("ISPK");
            Integer typeid = (Integer) row_hashmap.get("TYPEID");
            String typename = (String) row_hashmap.get("TYPENAME");
            Boolean isidentity = (Boolean) row_hashmap.get("ISIDENTITY");
            Boolean precisionrequired = (Boolean) row_hashmap.get("PRECISIONREQUIRED");
            Integer precision = (Integer) row_hashmap.get("PRECISION");
            Boolean scalerequired = (Boolean) row_hashmap.get("SCALEREQUIRED");
            Integer scale = (Integer) row_hashmap.get("SCALE");
            Integer order = (Integer) row_hashmap.get("ORDEN");

            Vector row_vector = new Vector();

            row_vector.add(schemaid);
            row_vector.add(tableid);
            row_vector.add(keyid);
            row_vector.add(name);
            row_vector.add(label);
            row_vector.add(ispk);
            row_vector.add(typeid);
            row_vector.add(typename);
            row_vector.add(precision);
            row_vector.add(scale);
            row_vector.add(order);

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
