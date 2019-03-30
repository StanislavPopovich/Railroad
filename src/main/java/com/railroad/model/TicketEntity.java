package com.railroad.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
@Getter
@Setter
public class TicketEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private TrainEntity trainEntity;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private PassengerEntity passengerEntity;
}
