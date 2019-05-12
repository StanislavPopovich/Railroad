package com.railroad.dto.schedule;

import lombok.Data;

import java.util.List;

@Data
public class ScheduleUpdateDto {
    private String oldDepartDateFromFirstStation;
    private Integer number;
    private List<String> stations;
    private List<String> arrivalDates;
    private List<String> departDates;
}
