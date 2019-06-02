package com.company.entities;

import com.company.BaseEntityIntegerPK;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.search.annotations.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;


@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "driver_location",
indexes = {
        @Index(name = "driver_idx", columnList = "driver_id")
})
@Entity
@Spatial(spatialMode = SpatialMode.HASH)
@Indexed
public class DriverLocation extends BaseEntityIntegerPK {

    @Column(name = "latitude", nullable = false)
    @Latitude
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    @Longitude
    private Double longitude;

    @Column(name = "accuracy", nullable = false)
    private Double accuracy;

    @Column(name = "time", nullable = false)
    private Long time;

    @Column(name = "raw_data", nullable = false)
    private String rawData;

    @Column(name = "driver_id", nullable = false)
    private Integer driverId;
}
