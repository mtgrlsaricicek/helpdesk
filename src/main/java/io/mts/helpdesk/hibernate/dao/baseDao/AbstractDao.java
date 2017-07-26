package io.mts.helpdesk.hibernate.dao.baseDao;

import io.mts.helpdesk.util.generics.Generics;
import org.hibernate.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Created by mtugrulsaricicek on 26.07.2017.
 */
public class AbstractDao<E> {

    private final SessionFactory sessionFactory;

    private final Class<?> entityClass;

    private final EntityManager entityManager;


    public AbstractDao(EntityManagerFactory entityManagerFactory){
        this.sessionFactory = requireNonNull(
                entityManagerFactory.unwrap(SessionFactory.class));
        entityManager = entityManagerFactory.createEntityManager();
        this.entityClass = Generics.getTypeParameter(getClass());
    }

    protected Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    protected EntityManager getEntityManager(){
        return this.entityManager;
    }

    protected Criteria criteria(){
        return currentSession().createCriteria(entityClass);
    }

    protected CriteriaQuery<E> criteriaQuery(){
        return entityManager.getCriteriaBuilder().createQuery(getEntityClass());
    }

    protected Query namedQuery(String queryName) throws HibernateException{
        return currentSession().getNamedQuery(requireNonNull(queryName));
    }


    @SuppressWarnings("unchecked")
    public Class<E> getEntityClass() {
        return (Class<E>) entityClass;
    }


    @SuppressWarnings("unchecked")
    protected E uniqueResult(Criteria criteria) throws HibernateException{
        return (E) requireNonNull(criteria).uniqueResult();
    }

    protected E uniqueResult(Query query) throws HibernateException{
        return (E) requireNonNull(query).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    protected List<E> list(Criteria criteria) throws HibernateException{
        return requireNonNull(criteria).list();
    }


    @SuppressWarnings("unchecked")
    protected E get(Serializable id) {
        return (E) currentSession().get(entityClass, requireNonNull(id));
    }

    protected E persist(E entity) throws HibernateException{
        currentSession().saveOrUpdate(requireNonNull(entity));
        return entity;
    }

    protected <T> T initialize(T proxy) throws HibernateException{
        if(!Hibernate.isInitialized(proxy)){
            Hibernate.initialize(proxy);
        }

        return proxy;
    }


    @SuppressWarnings("unchecked")
    protected  <T> T merge(T entity) {
        return (T) currentSession().merge(entity);
    }

    protected <T> void detach(T entity){
        currentSession().evict(entity);
    }

    protected void flush(){
        currentSession().flush();
    }
}
