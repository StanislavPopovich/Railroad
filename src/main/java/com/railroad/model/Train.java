package com.railroad.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "trains")
@Getter
@Setter
@ToString(exclude = "stations")
@EqualsAndHashCode(exclude = "stations")
public class Train extends BaseEntity {

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "start_station", nullable = false)
    private Long startStationId;

    @Column(name = "end_station", nullable = false)
    private Long endStationId;

    @ManyToMany
    @JoinTable(name = "train_stations", joinColumns = @JoinColumn(name = "train_id"),
            inverseJoinColumns = @JoinColumn(name = "station_id"))
    private Set<Station> stations;

    @Column(name = "seats", nullable = false)
    private Integer seats;

    @OneToMany(mappedBy = "train")
    private Set<Ticket> tickets;
}
