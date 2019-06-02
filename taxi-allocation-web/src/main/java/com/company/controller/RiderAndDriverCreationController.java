package com.company.controller;

import com.company.model.DriverModel;
import com.company.model.RiderModel;
import com.company.service.RiderAndDriverCreationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RiderAndDriverCreationController {

    private final RiderAndDriverCreationService riderAndDriverCreationService;

    @Autowired
    public RiderAndDriverCreationController(RiderAndDriverCreationService riderAndDriverCreationService) {
        this.riderAndDriverCreationService = riderAndDriverCreationService;
    }

    /**
     * API to insert  new rider details
     * @param riderModel
     * @return
     */
    @PostMapping(value = "/insert/rider", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RiderModel insertRider(@RequestBody RiderModel riderModel){
        return riderAndDriverCreationService.createRider(riderModel);

    }

    /**
     * API to insert new driver details
     * @param driverModel
     * @return
     */
    @PostMapping(value = "/insert/driver", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DriverModel insertDriver(@RequestBody DriverModel driverModel){
        return riderAndDriverCreationService.createDriver(driverModel);

    }
}
