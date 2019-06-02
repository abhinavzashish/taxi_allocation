package com.company.entities;

import com.company.BaseEntityIntegerPK;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "rider")
@Entity
public class Rider extends BaseEntityIntegerPK {


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "trips", nullable = false)
    @ColumnDefault("0")
    private Integer trips =0;

    @Column(name = "rating", nullable = false)
    @ColumnDefault("3.0")
    private Double rating=3.0;

    @Column(name = "engaged", nullable = false)
    @ColumnDefault("false")
    private Boolean engaged =false;

    @Column(name = "unique_id", nullable = false, unique = true)
    private String riderUniqueId;

    public String getRiderUniqueId() {
        return riderUniqueId;
    }

    public void setRiderUniqueId(String riderUniqueId) {
        this.riderUniqueId = riderUniqueId;
    }
}
