package com.railroad.dto;

import lombok.Data;

import java.util.Date;


@Data
public class ScheduleDto {
    private Date arrivalDate;
    private Date departDate;
    private Integer trainNumber;
    private String stationName;
}
