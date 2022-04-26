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
import io.github.xjrga.potatosql.data.dto.O_key;
import io.github.xjrga.potatosql.data.dto.O_table;
import java.util.List;
import javax.swing.*;

public class ListModelChildPK extends DefaultListModel {

    private final Dblink dbLink;

    public ListModelChildPK(Dblink dbLink) {

        this.dbLink = dbLink;

    }

    public void reload(O_table o) {
        clear();
        O_key input = new O_key();
        input.setSchema_id(o.getSchema_id());
        input.setTable_id(o.getTable_id());
        List<O_key> list = (List<O_key>) dbLink.find_pk(input);
        list.forEach(element -> addElement(element));
    }
}
