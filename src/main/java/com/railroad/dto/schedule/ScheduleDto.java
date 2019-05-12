package com.railroad.dto.schedule;

import lombok.Data;

@Data
public class ScheduleDto {

    private String arrivalDate;

    private String departDate;

    private String departDateFromFirstStation;

    private Integer trainNumber;

    private String stationName;
}
