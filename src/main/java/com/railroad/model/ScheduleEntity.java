package com.railroad.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "station_id", nullable = false)
    private StationEntity stationEntity;

    @OneToMany(mappedBy = "startData")
    private Set<TicketEntity> startTicket;

    @OneToMany(mappedBy = "endData")
    private Set<TicketEntity> destTicket;


}
