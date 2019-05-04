package com.railroad.dto.train;

import lombok.Data;

import java.util.List;

@Data
public class TrainTrasferTargetDto {
    private List<TrainTargetDto> trains;
    private String transferStation;
    private Integer seats;
}
