package com.company.dao;

import com.company.entities.Rider;

public interface RiderDao extends GenericDao<Rider, Integer> {

    Rider findByUniqueId(String uniqueId);

}
