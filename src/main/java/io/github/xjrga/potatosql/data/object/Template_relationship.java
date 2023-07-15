package io.github.xjrga.potatosql.data.object;

import java.util.Objects;

public class Template_relationship {
    private Boolean Is_identifying;
    private String Parent;
    private String Child;
    private String Parent_key;
    private String Child_key;
    public Template_relationship() {
    }
    public void setIs_identifying( Boolean Identifying ) {
        this.Is_identifying = Identifying;
    }
    public Boolean getIs_identifying() {
        return Is_identifying;
    }
    public String getParent() {
        return Parent;
    }
    public void setParent( String Parent ) {
        this.Parent = Parent;
    }
    public String getChild() {
        return Child;
    }
    public void setChild( String Child ) {
        this.Child = Child;
    }
    public String getParent_key() {
        return Parent_key;
    }
    public void setParent_key( String Parent_key ) {
        this.Parent_key = Parent_key;
    }
    public String getChild_key() {
        return Child_key;
    }
    public void setChild_key( String Child_key ) {
        this.Child_key = Child_key;
    }
    @Override
    public String toString() {
        return "Drelationship{" + "Is_identifying=" + Is_identifying + ", Parent=" + Parent + ", Child=" + Child + ", Parent_key=" + Parent_key + ", Child_key=" + Child_key + '}';
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode( this.Is_identifying );
        hash = 37 * hash + Objects.hashCode( this.Parent );
        hash = 37 * hash + Objects.hashCode( this.Child );
        hash = 37 * hash + Objects.hashCode( this.Parent_key );
        hash = 37 * hash + Objects.hashCode( this.Child_key );
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
