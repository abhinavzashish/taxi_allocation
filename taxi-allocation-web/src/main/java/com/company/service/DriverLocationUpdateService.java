package com.company.service;

import com.company.dao.DriverDao;
import com.company.dao.DriverLocationDao;
import com.company.entities.Driver;
import com.company.entities.DriverLocation;
import com.company.model.DriverLocationModel;
import com.company.util.MappingUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class DriverLocationUpdateService {

    private final DriverLocationDao driverLocationDao;
    private final DriverDao driverDao;
    private final Gson gson;

    @Autowired
    public DriverLocationUpdateService(DriverLocationDao driverLocationDao, DriverDao driverDao, Gson gson) {
        this.driverLocationDao = driverLocationDao;
        this.driverDao = driverDao;
        this.gson = gson;
    }

    public boolean updateDriverLocation(DriverLocationModel locationModel) throws Exception {
        Driver driver = driverDao.findByUniqueId(locationModel.getDriverUniqueId());
        if(driver==null){
            //TODO: create custom exception classes
            throw new Exception("Invalid driver Details.");
        }
        DriverLocation databaseDriverLocation = driverLocationDao.findByDriver(driver);
        databaseDriverLocation = MappingUtil.convertDriverLocationModelToEntity(locationModel, databaseDriverLocation);
        databaseDriverLocation.setDriverId(driver.getId());
        databaseDriverLocation.setTime(new Date().toInstant().getEpochSecond());
        databaseDriverLocation.setRawData(gson.toJson(locationModel));
        driverLocationDao.saveOrUpdate(databaseDriverLocation);
        return true;
    }
}
