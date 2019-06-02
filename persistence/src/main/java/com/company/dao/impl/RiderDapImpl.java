package com.company.dao.impl;

import com.company.dao.RiderDao;
import com.company.entities.Rider;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class RiderDapImpl extends GenericDaoImpl<Rider,Integer> implements RiderDao {

    @Override
    public Rider findByUniqueId(String uniqueId) {
        Criteria criteria = createCriteria(null)
                .add(Restrictions.eq("riderUniqueId", uniqueId));
        return (Rider) criteria.uniqueResult();
    }

    @Override
    public Rider save(Rider rider){
        rider.setRiderUniqueId(UUID.randomUUID().toString());
        return super.save(rider);
    }
}
