package com.railroad.dto.train;

import lombok.Data;

import java.util.List;

@Data
public class TrainTransferTargetDto {
    private List<TrainTargetDto> trains;
    private String transferStation;
    private Integer seats;
}
