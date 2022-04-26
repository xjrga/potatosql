package io.github.xjrga.potatosql.data.dto;

import java.util.Objects;

/**
 *
 * @author jr
 */
public class O_key_type {

    private int key_type_id;
    private String key_type_name;

    public O_key_type() {

    }

    public int getKey_type_id() {
        return key_type_id;
    }

    public void setKey_type_id(int key_type_id) {
        this.key_type_id = key_type_id;
    }

    public String getKey_type_name() {
        return key_type_name;
    }

    public void setKey_type_name(String key_type_name) {
        this.key_type_name = key_type_name;
    }

    @Override
    public String toString() {
        return key_type_name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final O_key_type other = (O_key_type) obj;
        return Objects.equals(this.key_type_name, other.key_type_name);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.key_type_name);
        return hash;
    }

    public String print() {
        return "O_key_type{" + "key_type_id=" + key_type_id + ", key_type_name=" + key_type_name + '}';
    }

}
