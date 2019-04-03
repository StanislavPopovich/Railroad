package com.railroad.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ways")
@Getter
@Setter
public class WayEntity extends BaseEntity{
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "first_station_id")
    private StationEntity firstStationEntity;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "second_station_id")
    private StationEntity secondStationEntity;

    @Column(name = "distance", nullable = false)
    private Double distance;

}
