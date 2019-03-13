package com.railroad.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "stations")
@Data
public class Station extends BaseEntity{

    @Column(name = "station_name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "stations")
    private Set<Train> trains;

    @OneToMany(mappedBy = "firstStation")
    private Set<Way> waysFromThisStation;

    @OneToMany(mappedBy = "secondStation")
    private Set<Way> waysToThisStation;


}
