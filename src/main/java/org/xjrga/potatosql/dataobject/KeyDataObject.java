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

public class KeyDataObject
{

    private Integer SchemaId;
    private Integer TableId;
    private Integer KeyId;
    private String Name;
    private String Label;
    private Boolean IsPk;
    private Integer TypeId;
    private Integer Precision;
    private Integer Scale;
    private Integer Orden;


    public KeyDataObject(Integer SchemaId, Integer TableId, Integer KeyId, String Name, String Label, Boolean IsPk, Integer TypeId, Integer Precision, Integer Scale, Integer Orden)
    {

        this.setSchemaId(SchemaId);
        this.setTableId(TableId);
        this.setKeyId(KeyId);
        this.setName(Name);
        this.setLabel(Label);
        this.setIsPk(IsPk);
        this.setTypeId(TypeId);
        this.setPrecision(Precision);
        this.setScale(Scale);
        this.setOrden(Orden);
    }


    public KeyDataObject()
    {

    }


    public Integer getSchemaId()
    {

        return SchemaId;
    }


    public void setSchemaId(Integer schemaId)
    {

        SchemaId = schemaId;
    }


    public Integer getTableId()
    {

        return TableId;
    }


    public void setTableId(Integer tableId)
    {

        TableId = tableId;
    }


    public Integer getKeyId()
    {

        return KeyId;
    }


    public void setKeyId(Integer keyId)
    {

        KeyId = keyId;
    }


    public String getLabel()
    {

        return Label;
    }


    public void setLabel(String label)
    {

        Label = label;
    }


    public Boolean getIsPk()
    {

        return IsPk;
    }


    public void setIsPk(Boolean isPk)
    {

        IsPk = isPk;
    }


    public Integer getTypeId()
    {

        return TypeId;
    }


    public void setTypeId(Integer typeId)
    {

        TypeId = typeId;
    }


    public Integer getPrecision()
    {

        return Precision;
    }


    public void setPrecision(Integer precision)
    {

        Precision = precision;
    }


    public Integer getOrden()
    {

        return Orden;
    }


    public void setOrden(Integer orden)
    {

        Orden = orden;
    }


    public boolean equals(Object object)
    {

        boolean flag = false;
        if (object instanceof KeyDataObject)
        {
            if (toString().equals(object.toString()))
            {
                flag = true;
            }
        }
        return flag;
    }


    @Override
    public String toString()
    {

        return getName();
    }


    public String getName()
    {

        return Name;
    }


    public void setName(String name)
    {

        Name = name;
    }


    public Integer getScale()
    {

        return Scale;
    }


    public void setScale(Integer scale)
    {

        Scale = scale;
    }

}
