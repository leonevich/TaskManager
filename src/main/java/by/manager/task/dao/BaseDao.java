package by.manager.task.dao;

import by.manager.task.utils.errors.exceptions.dao.DaoException;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

@Repository
public class BaseDao<T, PK extends Serializable> implements Dao<T, PK> {
    private static Logger log = Logger.getLogger(BaseDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @SuppressWarnings("unchecked")
    @Override
    public PK add(T object) throws DaoException {
        PK id;
        try {
            id = (PK) getSession().save(object);
            log.info("Add object with id: " + id);
            return (PK) id;
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void saveOrUpdate(T t) throws DaoException {
        try {
            getSession().saveOrUpdate(t);
            log.info("saveOrUpdate(t): " + t.getClass().getName());
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(T t) throws DaoException {
        try {
            getSession().update(t);
            log.info("update(t): " + t.getClass().getName());
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(Serializable id) throws DaoException {
        log.info("Get class by id: " + id);
        T t;
        try {
            t = (T) getSession().get(getPersistentClass(), id);
            Object object = t != null ? t.getClass().getName() : null;
            log.info("get clazz: " + object);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return t;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T load(Serializable id) throws DaoException {
        log.info("Load class by id: " + id);
        T t;
        try {
            t = (T) getSession().load(getPersistentClass(), id);
            Object object = t != null ? t.getClass().getName() : null;
            log.info("load() clazz: " + object);
            getSession().isDirty();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return t;
    }

    @Override
    public void delete(T t) throws DaoException {
        try {
            getSession().delete(t);
            log.info("Delete: " + t.getClass().getName());
        } catch (HibernateException e) {
            log.error("Error delete " + t.getClass().getName() + " in Dao " + e);
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Override
    public Query getQuery(String hql) throws DaoException {
        try {
            Query query = getSession().createQuery(hql);
            return query;
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
