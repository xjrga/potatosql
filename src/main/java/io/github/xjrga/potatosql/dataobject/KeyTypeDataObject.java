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

public class KeyTypeDataObject {

    private int typeid;
    private String name;
    private Boolean sizerequired;

    public KeyTypeDataObject(int typeid, String name, Boolean sizerequired) {

        this.setTypeId(typeid);
        this.setName(name);
        this.setSizerequired(sizerequired);
    }

    public int getTypeId() {

        return typeid;
    }

    public void setTypeId(int schemaid) {

        this.typeid = schemaid;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Boolean getSizerequired() {

        return sizerequired;
    }

    public void setSizerequired(Boolean sizerequired) {

        this.sizerequired = sizerequired;
    }

    public boolean equals(Object object) {

        boolean flag = false;
        if (object instanceof KeyTypeDataObject) {
            if (toString().equals(object.toString())) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public String toString() {

        return name;
    }

}
