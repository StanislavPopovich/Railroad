package com.railroad.dto;

import lombok.Data;

@Data
public class ScheduleDto {
    private String data;
    private Integer trainNumber;
    private String stationName;
}
