package com.railroad.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ScheduleDto {
    private Date date;
    private Integer trainNumber;
    private String stationName;
}
