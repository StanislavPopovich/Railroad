package com.railroad.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents UserEntity
 *
 * @author Stanislav Popovich
 * @version 1.0
 */


@Entity
@Table(name = "users")
@Setter
@Getter
public class UserEntity extends BaseEntity {

    @Size(min = 3, max = 25, message = "{name.size.error}")
    @Column(name = "username", nullable = false)
    private String userName;

    @Size(min = 3, message = "{password.size.error}")
    @Column(name = "password", nullable = false)
    private String password;


    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roleEntities;

    @ManyToMany
    @JoinTable(name = "user_passengers", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id"))
    private Set<PassengerEntity> passengerEntities;
}
