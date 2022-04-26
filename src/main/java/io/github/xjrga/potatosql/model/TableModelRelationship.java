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
import io.github.xjrga.potatosql.data.dto.O_relationship_multiple_select;
import io.github.xjrga.potatosql.data.dto.O_schema;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class TableModelRelationship extends DefaultTableModel {

    private Vector columns;
    private final Dblink dbLink;

    public TableModelRelationship(Dblink dbLink) {

        this.setColumnIdentifiers();
        this.dbLink = dbLink;
    }

    private void setColumnIdentifiers() {

        columns = new Vector();

        columns.add("RelationshipId");
        columns.add("SchemaId");
        columns.add("Parent_TableId");
        columns.add("Parent");
        columns.add("Child_TableId");
        columns.add("Child");
        columns.add("RelationshipTypeId");
        columns.add("Type");

        this.setColumnIdentifiers(columns);
    }

    @Override
    public Class getColumnClass(int i) {

        Class returnValue = Object.class;
        switch (i) {

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

        }
        return returnValue;
    }

    @Override
    public boolean isCellEditable(int i, int i1) {

        return false;
    }

    public void reload(O_schema schema) {

        Vector table = new Vector();
        List<O_relationship_multiple_select> list = (List<O_relationship_multiple_select>) dbLink.relationship_multiple_select(schema);
        for (O_relationship_multiple_select o : list) {
            //       SCHEMAID,
            //       PARENT_TABLEID,
            //       B.NAME AS PARENT,
            //       CHILD_TABLEID,
            //       C.NAME AS CHILD,
            //       RELATIONSHIPTYPEID,
            //       RELATIONSHIPID,
            //       D.NAME AS RELATIONSHIPTYPENAME
            Integer schemaid = o.getSchema_id();
            Integer parent_tableid = o.getParent_table_id();
            String parent = o.getParent();
            Integer child_tableid = o.getChild_table_id();
            String child = o.getChild();
            Integer relationshiptypeid = o.getRelationship_type_id();
            Integer relationshipid = o.getRelationship_id();
            String relationshiptypename = o.getRelationship_type_name();

            Vector row_vector = new Vector();

            row_vector.add(relationshipid);
            row_vector.add(schemaid);
            row_vector.add(parent_tableid);
            row_vector.add(parent);
            row_vector.add(child_tableid);
            row_vector.add(child);
            row_vector.add(relationshiptypeid);
            row_vector.add(relationshiptypename);

            table.add(row_vector);
        }

        this.setDataVector(table, columns);

    }

    public void clear() {

        Vector table = new Vector();
        this.setDataVector(table, columns);

    }

}
