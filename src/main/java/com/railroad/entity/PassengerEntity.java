package com.railroad.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents Passenger
 *
 * @author Stanislav Popovich
 *
 */

@Entity
@Table(name = "passengers")
@Getter
@Setter
public class PassengerEntity extends BaseEntity {

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "name", nullable = false)
    private String name;


    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @OneToMany(mappedBy = "passengerEntity")
    private Set<TicketEntity> ticketEntities;

    @ManyToMany(mappedBy = "passengerEntities")
    private Set<UserEntity> userEntities;
}
