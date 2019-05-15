package com.railroad.dto.train;

import lombok.Data;

/**
 * Data transfer object that represent train which user is searching in system
 *
 * @author Stanislav Popovich
 */

@Data
public class TrainTargetDto {
    private Integer number;
    private Integer seats;
    private String departDate;
    private String arrivalDate;
}
