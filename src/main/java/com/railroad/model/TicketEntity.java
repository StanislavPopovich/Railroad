package com.railroad.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
@Getter
@Setter
public class TicketEntity extends BaseEntity {

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "train_id", nullable = false)
    private TrainEntity trainEntity;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "passenger_id", nullable = false)
    private PassengerEntity passengerEntity;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "start_data", nullable = false)
    private ScheduleEntity startData;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "dest_data", nullable = false)
    private ScheduleEntity endData;
}
