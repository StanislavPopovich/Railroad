package com.railroad.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "trains")
@Getter
@Setter
public class TrainEntity extends BaseEntity {

    @Column(name = "number", nullable = false)
    private Integer number;


    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "train_stations", joinColumns = @JoinColumn(name = "train_id"),
            inverseJoinColumns = @JoinColumn(name = "station_id"))
    @OrderColumn(name = "id", nullable = false)
    private List<StationEntity> stationEntities;

    @Column(name = "seats", nullable = false)
    private Integer seats;

    @OneToMany(mappedBy = "trainEntity")
    private Set<TicketEntity> ticketEntities;

    @OneToMany(mappedBy = "trainEntity")
    private Set<ScheduleEntity> scheduleEntities;




}
