package com.railroad.dto;

import lombok.Data;

import java.util.List;

@Data
public class TrainDto {
    private Integer number;
    private Integer seats;
    private String startStation;
    private String endStation;
    private List<String> stations;
}
