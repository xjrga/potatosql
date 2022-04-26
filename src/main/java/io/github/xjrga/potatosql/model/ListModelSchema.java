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
import io.github.xjrga.potatosql.data.dto.O_schema;
import java.util.List;
import javax.swing.*;

public class ListModelSchema extends DefaultListModel {

    private Dblink dbLink;

    public ListModelSchema(Dblink dbLink) {
        this.dbLink = dbLink;
    }

    public void reload() {
        clear();
        List<O_schema> list = (List<O_schema>) dbLink.schema_select_all();
        list.forEach(element -> addElement(element));
    }

    public int find_by_schema_id(Integer schema_id) {
        int index = 0;
        int size = this.getSize();
        for (int i = 0; i < size; i++) {
            O_schema elementAt = (O_schema) this.getElementAt(i);
            if (elementAt.getSchema_id().equals(schema_id)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
