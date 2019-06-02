package com.company.controller;

import com.company.model.DriverModel;
import com.company.model.RideInputModel;
import com.company.service.TripAssignmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TripAssignmentController {

    private final TripAssignmentService tripAssignmentService;

    @Autowired
    public TripAssignmentController(TripAssignmentService tripAssignmentService){
        this.tripAssignmentService = tripAssignmentService;
    }

    /**
     * searches for a suitable driver for the trip requested by rider.
     * If driver is found, the driver and the rider are marked as engaged
     * @param rideInputModel
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/search/trip" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DriverModel getRide(@RequestBody RideInputModel rideInputModel) throws Exception {
        tripAssignmentService.validateRider(rideInputModel);
        DriverModel tripDetails = tripAssignmentService.getTripDetails(rideInputModel);
        if(tripDetails == null){
            log.error("No driver found for the trip");
        }
        return tripDetails;
    }


}
