package com.company.entities;

import com.company.BaseEntityIntegerPK;
import com.company.constants.VehicleType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "driver")
@Entity
public class Driver extends BaseEntityIntegerPK {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "vehicle_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Column(name = "vehicle_number", nullable = false)
    private String vehicleNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "trips_completed", nullable = false)
    @ColumnDefault("0")
    private Integer trips_completed=0;

    @Column(name = "rating", nullable = false)
    @ColumnDefault("3.0")
    private Double rating=3.0;

    @Column(name = "engaged", nullable = false)
    @ColumnDefault("false")
    private Boolean engaged=false;


    @Column(name = "unique_id", nullable = false, unique = true)
    private String driverUniqueId;

    public String getDriverUniqueId() {
        return driverUniqueId;
    }

    public void setDriverUniqueId(String driverUniqueId) {
        this.driverUniqueId = driverUniqueId;
    }

}
