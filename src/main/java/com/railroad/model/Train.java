package com.railroad.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "trains")
@Data
public class Train extends BaseEntity {

    @Column(name = "number", nullable = false)
    private Integer number;

    @ManyToMany
    @JoinTable(name = "train_stations", joinColumns = @JoinColumn(name = "train_id"),
            inverseJoinColumns = @JoinColumn(name = "station_id"))
    private Set<Station> stations;

    @Column(name = "seats", nullable = false)
    private Integer seats;
}
