package io.github.xjrga.potatosql.data.dao;

import java.util.List;

/**
 *
 * @author jr
 * @param <T>
 */
public interface Generic_dao<T> {

    /**
     * Persist the newInstance object into database
     *
     * @param instance
     */
    void insert(T instance);

    /**
     * Retrieve an object that was previously persisted to the database using
     * the indicated id as primary key
     *
     * @param instance
     * @return
     */
    List<T> find(T instance);

    /**
     *
     * @param instance
     * @return
     */
    List<T> find_all(T instance);

    /**
     * Save changes made to a persistent object.
     *
     * @param instance
     */
    void update(T instance);

    /**
     * Remove an object from persistent storage in the database
     *
     * @param instance
     */
    void delete(T instance);
}
