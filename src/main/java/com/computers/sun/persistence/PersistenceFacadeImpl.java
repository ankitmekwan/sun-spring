package com.computers.sun.persistence;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.computers.sun.exception.SpringException;

public class PersistenceFacadeImpl implements PersistenceFacade {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Session getHibernateSession() {
        return getSessionFactory().getCurrentSession();
    }

    @Override
    public void load(final BaseEntity obj) throws SpringException {
        getHibernateSession().load(obj, obj.getId());
    }

    @Override
    public void delete(final BaseEntity obj) throws SpringException {
        getHibernateSession().delete(obj);

    }

    @Override
    public void deleteAll(final List<? extends BaseEntity> lstObject)
            throws SpringException {
        for (final BaseEntity bean : lstObject) {
            getHibernateSession().delete(bean);
        }
    }

    @Override
    public void save(final BaseEntity obj) throws SpringException {
        getHibernateSession().save(obj);

    }

    @Override
    public void saveAll(final List<? extends BaseEntity> lstObject)
            throws SpringException {
        for (final BaseEntity bean : lstObject) {
            getHibernateSession().save(bean);
        }
    }

    @Override
    public void update(final BaseEntity obj) throws SpringException {
        getHibernateSession().saveOrUpdate(obj);
    }

    @Override
    public void updateAll(final List<? extends BaseEntity> lstObject)
            throws SpringException {
        for (final BaseEntity bean : lstObject) {
            getHibernateSession().saveOrUpdate(bean);
        }
    }

    @Override
    public List findByNamedQuery(final String queryName,
            final QueryOptions options) throws SpringException {
        final Session session = getSessionFactory().openSession();
        final Query query = session.getNamedQuery(queryName);

        for (final String paramName : query.getNamedParameters()) {
            final Object value = options.getParams().get(paramName);

            if (value == null) {
                throw new SpringException("Value for query parameter '"
                        + paramName + "' is null.");
            }
            if (value instanceof Collection) {
                query.setParameterList(paramName, (Collection) value);
            } else {
                query.setParameter(paramName, value);
            }
        }

        if (options.getMaxResults() != 0) {
            query.setMaxResults(options.getMaxResults());
        }
        if (options.getFirstResults() != -1) {
            query.setFirstResult(options.getFirstResults());
        }

        final List results = query.list();

        session.flush();
        session.close();

        return results;
    }

    @Override
    public List findByNamedQuery(final String queryName) {
        final Session session = getSessionFactory().openSession();
        final Query query = session.getNamedQuery(queryName);

        final List results = query.list();

        session.flush();
        session.close();

        return results;
    }

    @Override
    public List findByNamedQueryC(final String queryName,
            final QueryOptions options) throws SpringException {
        final Query query = getHibernateSession().getNamedQuery(queryName);

        for (final String paramName : query.getNamedParameters()) {
            final Object value = options.getParams().get(paramName);

            if (value == null) {
                throw new SpringException("Value for query parameter '"
                        + paramName + "' is null.");
            }
            if (value instanceof Collection) {
                query.setParameterList(paramName, (Collection) value);
            } else {
                query.setParameter(paramName, value);
            }
        }

        if (options.getMaxResults() != 0) {
            query.setMaxResults(options.getMaxResults());
        }

        if (options.getFirstResults() != -1) {
            query.setFirstResult(options.getFirstResults());
        }

        final List results = query.list();
        return results;
    }

    @Override
    public List findByNamedQueryC(final String queryName)
            throws SpringException {
        final Query query = getHibernateSession().getNamedQuery(queryName);
        final List results = query.list();
        return results;
    }

    @Override
    public int findByNamedExecuteQuery(final String queryName,
            final QueryOptions options) throws SpringException {
        final Session session = getSessionFactory().openSession();
        final Query query = session.getNamedQuery(queryName);

        for (final String paramName : query.getNamedParameters()) {
            final Object value = options.getParams().get(paramName);

            if (value == null) {
                throw new SpringException("Value for query parameter '"
                        + paramName + "' is null.");
            }

            query.setParameter(paramName, value);
        }

        if (options.getMaxResults() != 0) {
            query.setMaxResults(options.getMaxResults());
        }
        if (options.getFirstResults() != 0) {
            query.setFirstResult(options.getFirstResults());
        }

        final int results = query.executeUpdate();

        session.flush();
        session.close();

        return results;
    }

    @Override
    public int findByNamedExecuteQueryC(final String queryName,
            final QueryOptions options) throws SpringException {
        final Query query = getHibernateSession().getNamedQuery(queryName);
        for (final String paramName : query.getNamedParameters()) {
            final Object value = options.getParams().get(paramName);
            if (value != null) {
                query.setParameter(paramName, value);
            }
        }

        if (options.getMaxResults() != 0) {
            query.setMaxResults(options.getMaxResults());
        }

        if (options.getFirstResults() != 0) {
            query.setFirstResult(options.getFirstResults());
        }

        final int results = query.executeUpdate();
        return results;
    }

    @Override
    public List findByNativeQueryC(final String queryName,
            final QueryOptions options) throws SpringException {
        final Query query = getHibernateSession().createSQLQuery(queryName);

        for (final String paramName : query.getNamedParameters()) {
            final Object value = options.getParams().get(paramName);
            query.setParameter(paramName, value);
        }

        if (options.getMaxResults() != 0) {
            query.setMaxResults(options.getMaxResults());
        }

        final List results = query.list();
        return results;
    }

    @Override
    public void findByNativeQueryC(final String queryName)
            throws SpringException {
        final Query query = getHibernateSession().createSQLQuery(queryName);
        query.executeUpdate();
    }

    @Override
    public List findByNativeQueryCallProcedure(final String queryName,
            final QueryOptions options) throws SpringException {
        final Query query = getHibernateSession().createSQLQuery(queryName);

        for (final String paramName : query.getNamedParameters()) {
            final Object value = options.getParams().get(paramName);
            query.setParameter(paramName, value);
        }

        if (options.getMaxResults() != 0) {
            query.setMaxResults(options.getMaxResults());
        }

        final List results = query.list();
        return results;

    }

    @Override
    public List findByNativeQuery(final String queryName,
            final QueryOptions options) throws SpringException {
        final Session session = getSessionFactory().openSession();
        final Query query = session.createSQLQuery(queryName);

        for (final String paramName : query.getNamedParameters()) {

            final Object value = options.getParams().get(paramName);

            if (value == null) {
                throw new SpringException("Value for query parameter '"
                        + paramName + "' is null.");
            }

            query.setParameter(paramName, value);
        }

        if (options.getMaxResults() != 0) {
            query.setMaxResults(options.getMaxResults());
        }

        final List results = query.list();

        session.flush();
        session.close();

        return results;
    }

    @Override
    public void findByNativeQuery(final String queryName)
            throws SpringException {
        final Session session = getSessionFactory().openSession();
        final Query query = session.createSQLQuery(queryName);

        query.executeUpdate();

        session.flush();
        session.close();
    }
}
