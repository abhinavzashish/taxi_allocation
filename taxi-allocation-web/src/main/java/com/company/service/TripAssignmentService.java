package com.company.service;

import com.company.config.trip.TripConfig;
import com.company.dao.DriverDao;
import com.company.dao.DriverLocationDao;
import com.company.dao.RiderDao;
import com.company.entities.Driver;
import com.company.entities.DriverLocation;
import com.company.entities.Rider;
import com.company.model.DriverModel;
import com.company.model.RideInputModel;
import com.company.util.DistanceUtil;
import com.company.util.MappingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class TripAssignmentService {

    private final DriverDao driverDao;
    private final DriverLocationDao driverLocationDao;
    private final RiderDao riderDao;
    private TripConfig tripConfig;

    @Autowired
    public TripAssignmentService(DriverDao driverDao, DriverLocationDao driverLocationDao, RiderDao riderDao, TripConfig tripConfig) {
        this.driverDao = driverDao;
        this.driverLocationDao = driverLocationDao;
        this.riderDao = riderDao;
        this.tripConfig = tripConfig;
    }

    public void validateRider(RideInputModel rideInputModel) throws Exception {
        Rider rider = riderDao.findByUniqueId(rideInputModel.getRiderId());
        if(rider==null)
            throw new Exception("Invalid rider Details.");

        if(rider.getEngaged()){
            log.error("Rider : {} is already commuting. No trip assignment required.", rideInputModel.getRiderId());
            throw new Exception("You are already commuting. Please complete this trip before starting another trip.");
        }
    }


    public DriverModel getTripDetails(RideInputModel rideInputModel){
        List<DriverLocation> driverLocationList = getDriverList(rideInputModel);

        //TODO: use the commented section when spatial query is working
//        List<DriverModel> driverModels = new ArrayList<>();
//        for(DriverLocation dl : driverLocationList){
//            driverModels.add(getConvertedDriverModel(dl, 0.0));
//        }

        //TODo: comment below line when spatial query is working
        List<DriverModel> driverModels = filterDrivers(driverLocationList, rideInputModel);

        if(driverModels!=null && !driverModels.isEmpty()){
            DriverModel driverModel = driverModels.get(0);
            Driver byUniqueId = driverDao.findByUniqueId(driverModel.getDriverUniqueId());
            byUniqueId.setEngaged(true);
            driverDao.saveOrUpdate(byUniqueId);
            Rider rider = riderDao.findByUniqueId(rideInputModel.getRiderId());
            rider.setEngaged(true);
            return driverModel;
        }
        return null;
    }

    private List<DriverModel> filterDrivers(List<DriverLocation> driverList, RideInputModel rideInputModel) {
        List<DriverModel> filteredDrivers = new ArrayList<>();
        double distanceInMeter = tripConfig.getRadiusInKm()*1000;
            for (DriverLocation dl : driverList) {
                applyRestrictions(dl, filteredDrivers, rideInputModel, distanceInMeter);
            }
        return filteredDrivers;
    }

    private void applyRestrictions(DriverLocation dl, List<DriverModel> filteredDrivers, RideInputModel rideInputModel, double distance) {
        Double actualDistance = DistanceUtil.distanceBetweenLatLong(rideInputModel.getSourceLatitude(), dl.getLatitude(), rideInputModel.getSourceLongitude(), dl.getLongitude());
        if(actualDistance<=distance) {
            filteredDrivers.add(getConvertedDriverModel(dl, actualDistance));
        }
        if(!filteredDrivers.isEmpty()){
            filteredDrivers.sort(Comparator.comparing(o -> o.getDriverLocationModel().getTime()));
        }
        System.out.println(filteredDrivers);
    }

    private DriverModel getConvertedDriverModel(DriverLocation dl, double distance) {
        Driver driver = driverDao.findById(dl.getDriverId());
        DriverModel driverModel = MappingUtil.convertDriverToModel(driver);
        driverModel.setDriverLocationModel(MappingUtil.convertDriverLocationToModel(dl));
        driverModel.setDriverDistanceFromRider(distance);
        return driverModel;
    }

    private List<DriverLocation> getDriverList(RideInputModel rideInputModel) {
        List<Driver> nonEngagedDrivers = driverDao.findNonEngagedDrivers();


        //TODO: use this commented section when spatial query is working
//        return driverLocationDao.findDriversWithLocationCriteria(nonEngagedDrivers, tripConfig.getRadiusInKm(), rideInputModel.getSourceLatitude(), rideInputModel.getSourceLongitude(), tripConfig.getLocationConsiderationTimeInMinute());

        //TODo: comment below line when spatial query is working
        return driverLocationDao.findDriverLocations(nonEngagedDrivers, tripConfig.getLocationConsiderationTimeInMinute());
    }


}
