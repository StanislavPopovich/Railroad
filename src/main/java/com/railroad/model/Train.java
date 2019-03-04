package com.railroad.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "trains")
@Getter
@Setter
@ToString
public class Train extends BaseEntity {

    @Column(name = "number", nullable = false)
    private Integer number;

    private Set<Station> stations;

    @Column(name = "seats", nullable = false)
    private Integer seats;
}
