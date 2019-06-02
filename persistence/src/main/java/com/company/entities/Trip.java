package com.company.entities;

import com.company.BaseEntityIntegerPK;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "trip",
indexes = {
        @Index(name = "trip_rider_idx", columnList = "rider_id"),
        @Index(name = "trip_driver_idx", columnList = "driver_id")
})
@Entity
public class Trip extends BaseEntityIntegerPK {

    @Column(name = "rider_id", nullable = false)
    private Integer riderId;

    @Column(name = "driver_id", nullable = false)
    private Integer driverId;

    @Column(name = "source_address", nullable = false)
    private String sourceAddress;

    @Column(name = "destination_address", nullable = false)
    private String destinationAddress;

    @Column(name = "fare", nullable = false)
    private Double fare;


}
