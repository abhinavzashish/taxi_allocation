package com.company.util;

import com.company.entities.Driver;
import com.company.entities.DriverLocation;
import com.company.entities.Rider;
import com.company.model.DriverLocationModel;
import com.company.model.DriverModel;
import com.company.model.RiderModel;
import org.springframework.util.StringUtils;


public class MappingUtil {

    public static DriverModel convertDriverToModel(Driver driver){
        DriverModel driverModel = new DriverModel();
        if(StringUtils.hasText(driver.getAddress()))
            driverModel.setAddress(driver.getAddress());
        if(StringUtils.hasText(driver.getDriverUniqueId()))
            driverModel.setDriverUniqueId(driver.getDriverUniqueId());
        if(StringUtils.hasText(driver.getVehicleNumber()))
            driverModel.setVehicleNumber(driver.getVehicleNumber());
        if(StringUtils.hasText(driver.getName()))
            driverModel.setName(driver.getName());
        if(driver.getRating()!=null)
            driverModel.setRating(driver.getRating());
        if(driver.getTrips_completed()!=null)
            driverModel.setTrips(driver.getTrips_completed());
        if(driver.getEngaged()!=null)
            driverModel.setEngaged(driver.getEngaged());
        if(driver.getVehicleType()!=null)
            driverModel.setVehicleType(driver.getVehicleType());
        if(driver.getEngaged()!=null)
            driverModel.setEngaged(driver.getEngaged());
        return driverModel;
    }

    public static DriverLocationModel convertDriverLocationToModel(DriverLocation driverLocation) {
        DriverLocationModel locationModel = new DriverLocationModel();
        if(driverLocation.getAccuracy()!=null)
            locationModel.setAccuracy(driverLocation.getAccuracy());
        if(driverLocation.getLatitude()!=null)
            locationModel.setLatitude(driverLocation.getLatitude());
        if(driverLocation.getLongitude()!=null)
            locationModel.setLongitude(driverLocation.getLongitude());
        if(driverLocation.getTime()!=null)
            locationModel.setTime(driverLocation.getTime());

        return locationModel;
    }

    public static DriverLocation convertDriverLocationModelToEntity(DriverLocationModel driverLocationModel, DriverLocation driverLocation) {
        if(driverLocation == null)
            driverLocation= new DriverLocation();

        if(driverLocationModel.getAccuracy()!=null)
            driverLocation.setAccuracy(driverLocationModel.getAccuracy());
        if(driverLocationModel.getLatitude()!=null)
            driverLocation.setLatitude(driverLocationModel.getLatitude());
        if(driverLocationModel.getLongitude()!=null)
            driverLocation.setLongitude(driverLocationModel.getLongitude());
        if(driverLocationModel.getTime()!=null)
            driverLocation.setTime(driverLocationModel.getTime());

        return driverLocation;
    }

    public static Rider convertRiderModelToEntity(RiderModel riderModel) {
        Rider rider = new Rider();
        if(riderModel.getRating()!=null)
            rider.setRating(riderModel.getRating());
        if(riderModel.getTrips()!=null)
            rider.setTrips(riderModel.getTrips());
        rider.setName(riderModel.getName());
        rider.setAddress(riderModel.getAddress());

        return rider;
    }

    public static Driver convertDriverModelToEntity(DriverModel driverModel) {
        Driver driver = new Driver();
        if(driverModel.getRating()!=null)
            driver.setRating(driverModel.getRating());
        if(driverModel.getTrips()!=null)
            driver.setTrips_completed(driverModel.getTrips());
        driver.setName(driverModel.getName());
        driver.setAddress(driverModel.getAddress());
        driver.setVehicleType(driverModel.getVehicleType());
        driver.setVehicleNumber(driverModel.getVehicleNumber());

        return driver;
    }

    public static RiderModel convertRiderToModel(Rider rider) {
        RiderModel riderModel = new RiderModel();
        if(rider.getRating()!=null)
            riderModel.setRating(rider.getRating());
        if(rider.getTrips()!=null)
            riderModel.setTrips(rider.getTrips());
        riderModel.setName(rider.getName());
        riderModel.setAddress(rider.getAddress());
        riderModel.setRiderUniqueId(rider.getRiderUniqueId());
        riderModel.setEngaged(rider.getEngaged());

        return riderModel;
    }
}
