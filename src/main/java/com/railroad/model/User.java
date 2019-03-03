package com.railroad.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents User
 *
 * @author Stanislav Popovich
 * @version 1.0
 */


@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User extends BaseEntity {

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    private String confirmPassword;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
