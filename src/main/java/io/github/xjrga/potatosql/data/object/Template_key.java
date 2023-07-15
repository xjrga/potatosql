package io.github.xjrga.potatosql.data.object;

import java.util.Objects;

public class Template_key {
    private String datatype;
    private Boolean is_primary_key;
    private Boolean is_foreign_key;
    private String key_name;
    private Integer key_order;
    public Template_key() {
    }
    public Template_key( String key_name, Boolean is_primary_key, String datatype, Integer key_order, Boolean is_foreign_key ) {
        this.key_name = key_name;
        this.datatype = datatype;
        this.is_primary_key = is_primary_key;
        this.key_order = key_order;
        this.is_foreign_key = is_foreign_key;
    }
    public String getKey_name() {
        return key_name;
    }
    public String getDatatype() {
        return datatype;
    }
    public Boolean getIs_primary_key() {
        return is_primary_key;
    }
    public Boolean getIs_foreign_key() {
        return is_foreign_key;
    }
    public Integer getKey_order() {
        return key_order;
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode( this.datatype );
        hash = 89 * hash + Objects.hashCode( this.is_primary_key );
        hash = 89 * hash + Objects.hashCode( this.is_foreign_key );
        hash = 89 * hash + Objects.hashCode( this.key_name );
        hash = 89 * hash + Objects.hashCode( this.key_order );
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
    @Override
    public String toString() {
        return "Dkey{" + "datatype=" + datatype + ", is_primary_key=" + is_primary_key + ", is_foreign_key=" + is_foreign_key + ", key_name=" + key_name + ", key_order=" + key_order + '}';
    }
}
