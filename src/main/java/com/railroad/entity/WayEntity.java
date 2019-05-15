package com.railroad.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Simple JavaBean domain object that represents Way
 *
 * @author Stanislav Popovich
 *
 */

@Entity
@Table(name = "ways")
@Getter
@Setter
public class WayEntity extends BaseEntity{
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "first_station_id")
    private StationEntity firstStationEntity;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "second_station_id")
    private StationEntity secondStationEntity;

}
