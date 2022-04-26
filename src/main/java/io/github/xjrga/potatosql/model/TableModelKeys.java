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
import io.github.xjrga.potatosql.data.dto.O_key_with_name;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class TableModelKeys extends DefaultTableModel {

    private Vector columns;
    private final Dblink dbLink;

    public TableModelKeys(Dblink dbLink) {
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
                //Order
                returnValue = Integer.class;
        }
        return returnValue;
    }

    @Override
    public boolean isCellEditable(int i, int i1) {

        return false;
    }

    public void reload(O_key_with_name key) {

        Vector vector = new Vector();
        List<O_key_with_name> list = (List<O_key_with_name>) dbLink.key_type_select(key);
        for (O_key_with_name k : list) {
            Vector row_vector = new Vector();
            row_vector.add(k.getSchema_id());
            row_vector.add(k.getTable_id());
            row_vector.add(k.getTable_key_id());
            row_vector.add(k.getTable_key_name());
            row_vector.add(k.getTable_key_label());
            row_vector.add(k.getTable_key_is_pk());
            row_vector.add(k.getTable_key_type_id());
            row_vector.add(k.getKey_type_name());
            row_vector.add(k.getTable_key_order());
            vector.add(row_vector);
        }
        setDataVector(vector, columns);
    }

    public void clear() {
        Vector table = new Vector();
        setDataVector(table, columns);
    }

}
