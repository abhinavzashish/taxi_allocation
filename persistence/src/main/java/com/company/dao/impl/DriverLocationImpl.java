package com.company.dao.impl;


import com.company.dao.DriverLocationDao;
import com.company.entities.Driver;
import com.company.entities.DriverLocation;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.Unit;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class DriverLocationImpl extends GenericDaoImpl<DriverLocation, Integer> implements DriverLocationDao {


    @Override
    public DriverLocation findByDriver(Driver driver) {
        if(driver==null)
            return null;
        Criteria criteria = createCriteria(null);
        criteria.add(Restrictions.eq("driverId", driver.getId()));
        return (DriverLocation) criteria.uniqueResult();

    }

    //TODO: this spatial query is not working currently. we need to make this work.
    @Override
    public List<DriverLocation> findDriversWithLocationCriteria(List<Driver> drivers, Double radiusInKm, Double riderLat, Double riderLong, Integer timeAgoInMin) {

        List<DriverLocation> driverLocations = new ArrayList<>();

        List<Integer> driverIds = new ArrayList<>();
        Criteria criteria = createCriteria(null);
        drivers.forEach(d-> driverIds.add(d.getId()));
        criteria.add(Restrictions.in("driverId", driverIds));
        criteria.add(Restrictions.ge("time", new Date(System.currentTimeMillis() - (timeAgoInMin*60*1000)).toInstant().getEpochSecond()));


        FullTextSession fullTextSession = Search.getFullTextSession(super.currentSession());
        QueryBuilder builder = fullTextSession.getSearchFactory()
                .buildQueryBuilder().forEntity(DriverLocation.class).get();

        org.apache.lucene.search.Query luceneQuery = builder
                .spatial()
                .within(radiusInKm, Unit.KM)
                .ofLatitude(riderLat)
                .andLongitude(riderLong)
                .createQuery();

        Query hibQuery = fullTextSession
                .createFullTextQuery( luceneQuery, DriverLocation.class );
        hibQuery = ((FullTextQuery) hibQuery).setCriteriaQuery(criteria);
        List results = hibQuery.list();
        if(results!=null && !results.isEmpty()){
            for(Object obj : results){
                if(obj instanceof DriverLocation){
                    driverLocations.add((DriverLocation) obj);
                }
            }
        }

        return driverLocations;
    }

    @Override
    public List<DriverLocation> findDriverLocations(List<Driver> nonEngagedDrivers, Integer locationConsiderationTimeInMinute) {

        List<Integer> driverIds = new ArrayList<>();
        Criteria criteria = createCriteria(null);
        nonEngagedDrivers.forEach(d-> driverIds.add(d.getId()));
        criteria.add(Restrictions.in("driverId", driverIds));
        criteria.add(Restrictions.ge("time",
                new Date(System.currentTimeMillis() - (locationConsiderationTimeInMinute*60*1000))
                        .toInstant().getEpochSecond()));
        criteria.addOrder(Order.desc("time"));

        return criteria.list();
    }

}
