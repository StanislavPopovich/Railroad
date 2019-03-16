package com.railroad.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ways")
@Getter
@Setter
@ToString(exclude = {"firstStation", "secondStation"})
@EqualsAndHashCode(exclude = {"firstStation", "secondStation"})
public class Way extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "first_station_id")
    private Station firstStation;

    @ManyToOne
    @JoinColumn(name = "second_station_id")
    private Station secondStation;

    @Column(name = "distance", nullable = false)
    private Double distance;

}
