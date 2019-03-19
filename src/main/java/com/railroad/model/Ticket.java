package com.railroad.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@ToString(exclude = {"passenger, train"})
@EqualsAndHashCode(exclude = {"passenger, train"})
public class Ticket extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;
}
