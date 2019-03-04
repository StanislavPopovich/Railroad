package com.railroad.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stations")
@Getter
@Setter
@ToString
public class Station extends BaseEntity{

    @Column(name = "station_name", nullable = false)
    private String name;


}
