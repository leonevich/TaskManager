package by.manager.task.dao;


import by.manager.task.utils.errors.exceptions.dao.DaoException;
import org.hibernate.Query;

import java.io.Serializable;

public interface Dao<T, PK extends Serializable> {

    /**
     * @param object
     * @return
     * @throws DaoException
     */
    PK add(T object) throws DaoException;

    /**
     * @param t
     * @throws DaoException
     */
    void saveOrUpdate(T t) throws DaoException;

    void update(T t) throws DaoException;

    /**
     * @param id
     * @return
     * @throws DaoException
     */
    T get(Serializable id) throws DaoException;

    /**
     * @param id
     * @return
     * @throws DaoException
     */
    T load(Serializable id) throws DaoException;

    /**
     * @param t
     * @throws DaoException
     */
    void delete(T t) throws DaoException;

    /**
     * @param hql
     * @return
     * @throws DaoException
     */
    Query getQuery(String hql) throws DaoException;
}




