package com.company.service;

import com.company.dao.DriverDao;
import com.company.dao.RiderDao;
import com.company.entities.Driver;
import com.company.entities.Rider;
import com.company.model.DriverModel;
import com.company.model.RiderModel;
import com.company.util.MappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiderAndDriverCreationService {

    private final RiderDao riderDao;
    private final DriverDao driverDao;

    @Autowired
    public RiderAndDriverCreationService(RiderDao riderDao, DriverDao driverDao) {
        this.riderDao = riderDao;
        this.driverDao = driverDao;
    }

    public RiderModel createRider(RiderModel riderModel){
        Rider rider = MappingUtil.convertRiderModelToEntity(riderModel);
        rider=riderDao.save(rider);
        return MappingUtil.convertRiderToModel(rider);
    }

    public DriverModel createDriver(DriverModel driverModel) {
        Driver driver = MappingUtil.convertDriverModelToEntity(driverModel);
        driver=driverDao.save(driver);
        return MappingUtil.convertDriverToModel(driver);
    }
}
