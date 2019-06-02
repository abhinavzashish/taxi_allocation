package com.company.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TripModel {

    private String riderUniqueId;
    private String driverUniqueId;
    private String sourceAddress;
    private String destinationAddress;
    private Double fare;

}
