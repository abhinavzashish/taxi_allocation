package com.company.dao;

import com.company.entities.Driver;

import java.util.List;

public interface DriverDao extends GenericDao<Driver, Integer> {

    List<Driver> findEngagedDrivers();
    List<Driver> findNonEngagedDrivers();
    Driver findByUniqueId(String uniqueId);

}
