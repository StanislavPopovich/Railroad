package com.railroad.model;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents User
 *
 * @author Stanislav Popovich
 * @version 1.0
 */


@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {

    @Size(min = 3, max = 25, message = "{name.size.error}")
    @Column(name = "username", nullable = false)
    private String userName;

    @Size(min = 3, message = "{password.size.error}")
    @Column(name = "password", nullable = false)
    private String password;


    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(name = "user_passengers", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id"))
    private Set<Passenger> passengers;
}
