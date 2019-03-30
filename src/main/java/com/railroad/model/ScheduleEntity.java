package com.railroad.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "schedules")
@Getter
@Setter
public class ScheduleEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private TrainEntity trainEntity;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
    private Date dataAndTime;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private StationEntity stationEntity;


}
