package com.railroad.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ways")
@Data
public class Way extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "first_station_id")
    //@Column(name = "first_station_id", nullable = false)
    private Station firstStation;

    @ManyToOne
    @JoinColumn(name = "second_station_id")
    //@Column(name = "second_station_id", nullable = false)
    private Station secondStation;

    @Column(name = "distance", nullable = false)
    private Double distance;

}
