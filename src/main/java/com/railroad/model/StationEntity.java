package com.railroad.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "stations")
@Getter
@Setter
@ToString
public class StationEntity extends BaseEntity{

    @Column(name = "station_name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "stationEntities")
    private Set<TrainEntity> trainEntities;

    @OneToMany(mappedBy = "firstStationEntity")
    private Set<WayEntity> waysFromThisStation;

    @OneToMany(mappedBy = "secondStationEntity")
    private Set<WayEntity> waysToThisStation;




}
