package com.railroad.dto.train;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainScheduleDto {
    private Integer number;
    private String departDate;
    private String arrivalDate;
}
