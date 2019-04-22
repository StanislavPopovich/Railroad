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

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "passenger_id", nullable = false)
    private PassengerEntity passengerEntity;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "depart_date", nullable = false)
    private ScheduleEntity departDate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "arrival_date", nullable = false)
    private ScheduleEntity arrivalDate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;
}
