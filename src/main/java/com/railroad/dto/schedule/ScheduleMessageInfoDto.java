package com.railroad.dto.schedule;

import lombok.Data;

import java.util.List;

@Data
public class ScheduleMessageInfoDto {
    private String arrivalDate;
    private String departDate;
    private String departDateFromFirstStation;
    private Integer train;
    private String station;
    private List<String> stations;
}
