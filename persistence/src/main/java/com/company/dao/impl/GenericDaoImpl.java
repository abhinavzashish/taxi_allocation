package com.company.dao.impl;

import com.company.GenericEntity;
import com.company.dao.GenericDao;
import com.company.entities.Rider;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.core.annotation.AnnotationUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Transactional
public class GenericDaoImpl <E extends GenericEntity<PK>, PK extends Serializable> implements GenericDao<E, PK> {

    @PersistenceContext
    private EntityManager entityManager;


    public E findById(PK id) {
        E entity = currentSession().get(getPersistenceClass(), id);
        if (entity == null) {
            return null;
        }
        return entity;
    }

    private Class<E> getPersistenceClass(){
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        Class<E> type = (Class<E>) superClass.getActualTypeArguments()[0];
        return type;
    }

    public List<E> findAll() {
        return createCriteria(null).list();
    }

    public E save(E entity) {
        if (entity == null) {
            return null;
        }
        currentSession().save(entity);
        return entity;
    }

    public E update(E entity) {
        return saveOrUpdate(entity);
    }

    public E saveOrUpdate(E entity) {
        if (entity == null) {
            return null;
        }
        currentSession().saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void saveOrUpdateAll(Collection<E> entities) {
        if (entities == null || entities.isEmpty()) {
            return;
        }
        for(E e : entities) {
            saveOrUpdate(e);
        }
    }

    public void delete(E entity) {
        if (entity == null) {
            return;
        }
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    public void deleteById(PK id) {
        E e = findById(id);
        delete(e);
    }

    protected Session currentSession() {
        return entityManager.unwrap(Session.class);
    }


    protected Criteria createCriteria(String alias){
        Criteria criteria = currentSession().createCriteria(getPersistenceClass(), alias);
        CriteriaImpl crit = (CriteriaImpl)criteria;
        criteria = new CriteriaImpl(getPersistenceClass().getName(), alias, crit.getSession());
        Cache cache = AnnotationUtils.findAnnotation(getPersistenceClass(), Cache.class);
        if(cache != null && !Objects.equals(CacheConcurrencyStrategy.NONE, cache.usage()))
            criteria.setCacheable(true);
        return criteria;
    }


    //Never use this unless its a life and death situation
    @Deprecated
    protected void flush() {
        currentSession().flush();
    }
}
