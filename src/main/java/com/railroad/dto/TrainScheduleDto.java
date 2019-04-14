package com.railroad.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainScheduleDto {
    private Integer number;
    private String departDate;
    private String arrivalDate;
}
