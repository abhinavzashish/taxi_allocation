package com.company.dao.impl;

import com.company.dao.DriverDao;
import com.company.entities.Driver;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class DriverDaoImpl extends GenericDaoImpl<Driver, Integer> implements DriverDao {


    @Override
    public List<Driver> findEngagedDrivers() {
        Criteria criteria = createCriteria(null)
                .add(Restrictions.eq("engaged", true));
        return criteria.list();
    }

    @Override
    public List<Driver> findNonEngagedDrivers() {
        Criteria criteria = createCriteria(null)
                .add(Restrictions.eq("engaged", false));
        return criteria.list();
    }

    @Override
    public Driver findByUniqueId(String uniqueId) {
        Criteria criteria = createCriteria(null)
                .add(Restrictions.eq("driverUniqueId", uniqueId));
        return (Driver) criteria.uniqueResult();
    }

    @Override
    public Driver save(Driver driver){
        driver.setDriverUniqueId(UUID.randomUUID().toString());
        return super.save(driver);
    }
}
