package com.railroad.dto.train;

import lombok.Data;

/**
 * Data transfer object that represent train with arrival date and departure date
 *
 * @author Stanislav Popovich
 */

@Data
public class TrainScheduleDto {
    private Integer number;
    private String departDate;
    private String arrivalDate;
}
