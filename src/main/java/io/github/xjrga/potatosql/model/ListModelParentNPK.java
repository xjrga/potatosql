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

import io.github.xjrga.potatosql.data.DbLink;
import io.github.xjrga.potatosql.dataobject.KeyDataObject;

import javax.swing.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class ListModelParentNPK extends DefaultListModel {

    private DbLink dbLink;

    public ListModelParentNPK(DbLink dbLink) {

        this.dbLink = dbLink;

    }

    public void reload(int schemaid, int tableid) {

        this.clear();

        LinkedList list = (LinkedList) dbLink.TableKey_Select_NPK(schemaid, tableid);
        Iterator it = list.iterator();

        while (it.hasNext()) {

            HashMap row = (HashMap) it.next();
            schemaid = (Integer) row.get("SCHEMAID");
            tableid = (Integer) row.get("TABLEID");
            Integer keyid = (Integer) row.get("KEYID");
            String name = (String) row.get("NAME");
            String label = (String) row.get("LABEL");
            Boolean ispk = (Boolean) row.get("ISPK");
            Integer typeid = (Integer) row.get("TYPEID");
            Integer precision = (Integer) row.get("PRECISION");
            Integer scale = (Integer) row.get("SCALE");
            Integer order = (Integer) row.get("ORDERING");
            KeyDataObject keyDataObject = new KeyDataObject(schemaid, tableid, keyid, name, label, ispk, typeid, precision, scale, order);

            this.addElement(keyDataObject);
        }

    }

}
