package com.railroad.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents Station
 *
 * @author Stanislav Popovich
 *
 */

@Entity
@Table(name = "stations")
@Getter
@Setter
public class StationEntity extends BaseEntity{

    @Column(name = "station_name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "stationEntities")
    private Set<TrainEntity> trainEntities;

    @OneToMany(mappedBy = "firstStationEntity")
    private Set<WayEntity> waysFromThisStation;

    @OneToMany(mappedBy = "secondStationEntity")
    private Set<WayEntity> waysToThisStation;

    @OneToMany(mappedBy = "stationEntity")
    private Set<ScheduleEntity> scheduleEntities;




}
