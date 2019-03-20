package com.railroad.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "passengers")
@Getter
@Setter
@ToString(exclude = {"tickets, users"})
@EqualsAndHashCode(exclude = {"tickets, users"})
public class Passenger extends BaseEntity {

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "name", nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date", nullable = false)
    private Date BirthDate;

    @OneToMany(mappedBy = "passenger")
    private Set<Ticket> tickets;

    @ManyToMany(mappedBy = "passengers")
    private Set<User> users;
}
