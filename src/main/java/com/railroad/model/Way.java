package com.railroad.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ways")
@Data
public class Way extends BaseEntity{

    @Column(name = "f_station_id", nullable = false)
    private Long firstStationId;

    @Column(name = "s_station_id", nullable = false)
    private Long secondStationId;

    @Column(name = "distance", nullable = false)
    private Double distance;

}
