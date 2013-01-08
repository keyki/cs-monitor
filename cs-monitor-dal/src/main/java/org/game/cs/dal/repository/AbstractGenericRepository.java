package org.game.cs.dal.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class AbstractGenericRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;
    private Class<T> type;

    public AbstractGenericRepository(Class<T> type) {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return entityManager.createQuery("SELECT u FROM " + type.getSimpleName() + " u").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<T> runCustomQuery(Query query) {
        return query.getResultList();
    }

    public T findById(Number id) {
        return entityManager.find(type, id);
    }

    @SuppressWarnings("unchecked")
    public T findByCustomField(String key, String value) {
        String queryString = "SELECT u FROM " + type.getCanonicalName() + " u WHERE u." + key + " = '" + value + "'";
        Query query = entityManager.createQuery(queryString);
        return (T) query.getSingleResult();
    }

    public void persist(T t) {
        entityManager.persist(t);
    }

    public void remove(T t) {
        entityManager.remove(t);
    }

    public T merge(T t) {
        T t2 = entityManager.merge(t);
        return t2;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
