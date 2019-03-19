package com.railroad.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents Role
 *
 * @author Stanislav Popovich
 * @version 1.0
 */

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString(exclude = "users")
@EqualsAndHashCode(exclude = "users")
public class Role extends BaseEntity {

    @Column(name = "role_name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
