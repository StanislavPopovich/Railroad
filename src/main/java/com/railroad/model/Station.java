package com.railroad.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "stations")
@Data
public class Station extends BaseEntity{

    @Column(name = "station_name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "stations")
    private Train train;


}
