package com.railroad.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents Role
 *
 * @author Stanislav Popovich
 *
 */

@Entity
@Table(name = "roles")
@Getter
@Setter
public class RoleEntity extends BaseEntity {

    @Column(name = "role_name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roleEntities")
    private Set<UserEntity> userEntities;
}
