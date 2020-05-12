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

package org.xjrga.potatosql.dataobject;

public class SchemaDataObject {

    private int schemaid;
    private String schemaname;


    public SchemaDataObject(int tableid, String tablename) {

        this.setSchemaId(tableid);
        this.setSchemaName(tablename);
    }


    public int getSchemaId() {

        return schemaid;
    }


    public void setSchemaId(int schemaid) {

        this.schemaid = schemaid;
    }


    public String getSchemaName() {

        return schemaname;
    }


    public void setSchemaName(String schemaname) {

        this.schemaname = schemaname;
    }


    @Override
    public String toString() {
        //return "{"+schemaid+":"+schemaname+"}";
        return schemaname;
    }

}
