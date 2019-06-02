package com.company.controller;

import com.company.model.DriverLocationModel;
import com.company.service.DriverLocationUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DriverLocationController {

    private final DriverLocationUpdateService locationUpdateService;

    @Autowired
    public DriverLocationController(DriverLocationUpdateService locationUpdateService) {
        this.locationUpdateService = locationUpdateService;
    }

    @PostMapping(value = "/populate/location" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DriverLocationModel updateCurrentLocation(@RequestBody DriverLocationModel locationModel) throws Exception {
        boolean updated = locationUpdateService.updateDriverLocation(locationModel);
        locationModel.setUpdated(updated);
        return locationModel;
    }


}
