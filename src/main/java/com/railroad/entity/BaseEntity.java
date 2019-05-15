package com.railroad.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Base class with property ID
 *
 * @author Stanislav Popovich
 */

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
}
