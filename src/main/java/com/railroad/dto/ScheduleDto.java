package com.railroad.dto;

import lombok.Data;

import java.util.Date;


@Data
public class ScheduleDto {
    private String arrivalDate;
    private String departDate;
    private String departDateFromFirstStation;
    private Integer trainNumber;
    private String stationName;
}
