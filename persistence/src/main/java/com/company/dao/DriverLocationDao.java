package com.company.dao;

import com.company.entities.Driver;
import com.company.entities.DriverLocation;

import java.util.List;

public interface DriverLocationDao extends GenericDao<DriverLocation, Integer> {

    DriverLocation findByDriver(Driver driver);

    /**
     * finds the driver based on rider and driver location, distance-radius, recent-time etc
     * Also uses @Spatial queries (Full text queries)
     * @param nonEngagedDrivers
     * @param radiusInKm
     * @param riderLat
     * @param riderLong
     * @param timeAgoInMin
     * @return
     */
    List<DriverLocation> findDriversWithLocationCriteria(List<Driver> nonEngagedDrivers, Double radiusInKm, Double riderLat, Double riderLong, Integer timeAgoInMin);

    List<DriverLocation> findDriverLocations(List<Driver> nonEngagedDrivers, Integer locationConsiderationTimeInMinute);

}
