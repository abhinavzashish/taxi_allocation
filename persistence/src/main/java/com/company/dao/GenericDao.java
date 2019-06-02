package com.company.dao;

import com.company.GenericEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface GenericDao<E extends GenericEntity<PK>, PK extends Serializable> {

    E findById(PK id);
    List<E> findAll();
    E save(E entity);
    E update(E entity);
    E saveOrUpdate(E entity);
    void saveOrUpdateAll(Collection<E> entities);
    void delete(E entity);
    void deleteById(PK id);

}
