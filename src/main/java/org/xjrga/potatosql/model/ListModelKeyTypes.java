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
import org.xjrga.potatosql.dataobject.KeyTypeDataObject;

import javax.swing.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class ListModelKeyTypes extends DefaultListModel {

    private final DbLink dbLink;


    public ListModelKeyTypes(DbLink dbLink) {

        this.dbLink = dbLink;

    }


    public void reload() {

        this.clear();

        LinkedList list = (LinkedList) dbLink.KeyType_Select_All();
        Iterator it = list.iterator();

        while (it.hasNext()) {

            HashMap row = (HashMap) it.next();
            int schemaid = (int) row.get("TYPEID");
            String name = (String) row.get("NAME");
            Boolean sizerequired = (Boolean) row.get("SIZEREQUIRED");
            KeyTypeDataObject keyTypeDataObject = new KeyTypeDataObject(schemaid, name, sizerequired);

            this.addElement(keyTypeDataObject);
        }

    }

}
