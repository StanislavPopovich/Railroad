package com.railroad.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "passengers")
@Getter
@Setter
@ToString
public class Passenger extends BaseEntity {

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birt_hdate", nullable = false)
    private Date BirthDate;

    @OneToMany(mappedBy = "passenger")
    private Set<Ticket> tickets;
}
