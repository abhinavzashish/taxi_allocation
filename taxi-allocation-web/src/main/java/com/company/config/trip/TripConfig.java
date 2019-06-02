package com.company.config.trip;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("trip.config")
public class TripConfig {

    private Double radiusInKm;
    private Integer locationConsiderationTimeInMinute;

}
