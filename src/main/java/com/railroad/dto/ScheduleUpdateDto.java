package com.railroad.dto;

import lombok.Data;

@Data
public class ScheduleUpdateDto {
    private String arrivalDate;
    private String departDate;
    private String departDateFromFirstStation;
    private String oldDepartDateFromFirstStation;
    private Integer trainNumber;
    private String stationName;
}
