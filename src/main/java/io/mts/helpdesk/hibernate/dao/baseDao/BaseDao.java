package io.mts.helpdesk.hibernate.dao.baseDao;

import io.mts.helpdesk.hibernate.entity.baseentity.BaseEntity;
import org.hibernate.Criteria;
import org.hibernate.Transaction;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Created by mtugrulsaricicek on 26.07.2017.
 */
public class BaseDao<T extends BaseEntity> extends AbstractDao<T> {

    @Inject
    public BaseDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    public <E> List<E> findAll(Class<? extends BaseEntity> clazz){

        Criteria criteria = currentSession().createCriteria(clazz);
        List<E> list = criteria.list();
        return list;
    }

    public T findById(Serializable oid) {
        return get(oid);
    }

    @SuppressWarnings("unchecked")
    public T findById(Class<? extends BaseEntity> clazz, Serializable oid){
        return (T) currentSession().get(clazz,requireNonNull(oid));
    }

    public T create(T entity){
        Transaction transaction = currentSession().beginTransaction();
        entity =  persist(entity);
        transaction.commit();
        return entity;
    }

    public T update(T entity){
        Transaction transaction = currentSession().beginTransaction();
        entity = persist(entity);
        transaction.commit();
        return entity;
    }

    public T delete(T entity){
        Transaction transaction = currentSession().beginTransaction();
        entity = merge(entity);
        currentSession().delete(entity);
        transaction.commit();
        return entity;
    }

}
