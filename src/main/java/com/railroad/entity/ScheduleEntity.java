package com.railroad.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "schedules")
@Getter
@Setter
public class ScheduleEntity extends BaseEntity {

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "train_id", nullable = false)
    private TrainEntity trainEntity;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "arrival_date", nullable = false)
    private Date arrivalDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "depart_date", nullable = false)
    private Date departDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "depart_date_first_station", nullable = false)
    private Date departDateFromFirstStation;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "station_id", nullable = false)
    private StationEntity stationEntity;

    @OneToMany(mappedBy = "departDate")
    private Set<TicketEntity> departDateTicket;

    @OneToMany(mappedBy = "arrivalDate")
    private Set<TicketEntity> arrivalDateTicket;


}
