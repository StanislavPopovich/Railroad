package com.railroad.dto.schedule;

import lombok.Data;

import java.util.List;

@Data
public class ScheduleTrainDto {
    private Integer number;
    private List<String> stations;
    private List<String> arrivalDates;
    private List<String> departDates;
}
