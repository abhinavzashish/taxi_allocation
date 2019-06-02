package com.company.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DriverLocationModel {

    private Double latitude;
    private Double longitude;
    private Double accuracy;
    private Long time;
    private String driverUniqueId;
    private boolean updated;
}
