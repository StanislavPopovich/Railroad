package com.railroad.dto;

import lombok.Data;

import java.util.List;

@Data
public class ScheduleInfoDto {
    private String arrivalDate;
    private String departDate;
    private String departDateFromFirstStation;
    private Integer train;
    private String station;
    private List<String> stations;
}
