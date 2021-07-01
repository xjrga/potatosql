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
package io.github.xjrga.potatosql.dataobject;

public class TableDataObject {

    private int schemaid;
    private int tableid;
    private String tablename;

    public TableDataObject(int schemaid, int tableid, String tablename) {

        this.setSchemaId(schemaid);
        this.setTableId(tableid);
        this.setTableName(tablename);
    }

    public int getSchemaId() {

        return schemaid;
    }

    public void setSchemaId(int schemaid) {

        this.schemaid = schemaid;
    }

    public int getTableId() {

        return tableid;
    }

    public void setTableId(int tableid) {

        this.tableid = tableid;
    }

    public String getTableName() {

        return tablename;
    }

    public void setTableName(String tablename) {

        this.tablename = tablename;
    }

    @Override
    public String toString() {

        return tablename;
    }

    public boolean equals(Object object) {

        boolean flag = false;
        if (object instanceof TableDataObject) {
            if (getString().equals(((TableDataObject) object).getString())) {
                flag = true;
            }
        }
        return flag;
    }

    public String getString() {

        return "{" + schemaid + ":" + tableid + ":" + tablename + "}";
    }

}
