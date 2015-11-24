package com.computers.sun.persistence;

import java.util.List;

import org.hibernate.Session;

import com.computers.sun.exception.SpringException;

public interface PersistenceFacade {

    public Session getHibernateSession();

    public void load(BaseEntity obj) throws SpringException;

    public void delete(BaseEntity obj) throws SpringException;

    public void deleteAll(List<? extends BaseEntity> lstObject)
            throws SpringException;

    public void save(BaseEntity obj) throws SpringException;

    public void saveAll(List<? extends BaseEntity> baseEntityList)
            throws SpringException;

    public void update(BaseEntity obj) throws SpringException;

    public void updateAll(List<? extends BaseEntity> lstObject)
            throws SpringException;

    public List findByNamedQuery(String queryName, QueryOptions options)
            throws SpringException;

    public List findByNamedQuery(String queryName) throws SpringException;

    public List findByNamedQueryC(String queryName, QueryOptions options)
            throws SpringException;

    public List findByNamedQueryC(String queryName) throws SpringException;

    public int findByNamedExecuteQuery(String queryName, QueryOptions options)
            throws SpringException;

    public int findByNamedExecuteQueryC(String queryName, QueryOptions options)
            throws SpringException;

    public List findByNativeQueryC(final String queryName,
            final QueryOptions options) throws SpringException;

    public void findByNativeQueryC(String queryName) throws SpringException;

    public List findByNativeQueryCallProcedure(final String queryName,
            final QueryOptions options) throws SpringException;

    public List findByNativeQuery(String queryName, QueryOptions options)
            throws SpringException;

    public void findByNativeQuery(String queryName) throws SpringException;
}
