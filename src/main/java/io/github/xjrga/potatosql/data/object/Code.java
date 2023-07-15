package io.github.xjrga.potatosql.data.object;

import java.util.Objects;

public class Code {
    private String dialect;
    private String category;
    private String action;
    private String name;
    private String path;
    private String order;
    public Code() {
    }
    public String getDialect() {
        return dialect;
    }
    public void setDialect( String dialect ) {
        this.dialect = dialect;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory( String category ) {
        this.category = category;
    }
    public String getAction() {
        return action;
    }
    public void setAction( String action ) {
        this.action = action;
    }
    public String getName() {
        return name;
    }
    public void setName( String name ) {
        this.name = name;
    }
    public String getPath() {
        return path;
    }
    public void setPath( String path ) {
        this.path = path;
    }
    public String getOrder() {
        return order;
    }
    public void setOrder( String order ) {
        this.order = order;
    }
    public Code_sub getCode_sub() {
        return new Code_sub( action, hashCode() );
    }
    @Override
    public String toString() {
        return dialect + ", " + category + ", " + action;
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode( this.path );
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
