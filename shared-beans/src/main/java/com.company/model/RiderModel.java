package com.company.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RiderModel {

    private String name;
    private String address;
    private Integer trips;
    private Double rating;
    @JsonProperty(value = "id")
    private String riderUniqueId;
    private boolean engaged;

}
