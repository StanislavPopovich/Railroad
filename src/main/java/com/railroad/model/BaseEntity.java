package com.railroad.model;
import lombok.Data;

import javax.persistence.*;

/**
 * Base class with property ID
 *
 * @author Stanislav Popovich
 * @version 1.0
 */

@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
}
