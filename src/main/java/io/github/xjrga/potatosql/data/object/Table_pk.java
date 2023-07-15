package io.github.xjrga.potatosql.data.object;

import java.util.Objects;

public class Table_pk {
    private Integer Schema_id;
    private Integer Table_id;
    public Table_pk() {
    }
    public Table_pk( Integer Schema_id, Integer Table_id ) {
        this.Schema_id = Schema_id;
        this.Table_id = Table_id;
    }
    public void setSchema_id( Integer Schema_id ) {
        this.Schema_id = Schema_id;
    }
    public Integer getSchema_id() {
        return Schema_id;
    }
    public void setTable_id( Integer Table_id ) {
        this.Table_id = Table_id;
    }
    public Integer getTable_id() {
        return Table_id;
    }
    @Override
    public String toString() {
        return "Table_pk{" + "Schema_id=" + Schema_id + ", Table_id=" + Table_id + '}';
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode( this.Schema_id );
        hash = 59 * hash + Objects.hashCode( this.Table_id );
        return hash;
    }
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        return hashCode() == obj.hashCode();
    }
}
