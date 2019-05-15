package com.railroad.dto.train;

import lombok.Data;

import java.util.List;

/**
 * Data transfer object that represent trains with transfer which user is searching in system
 *
 * @author Stanislav Popovich
 */

@Data
public class TrainTransferTargetDto {
    private List<TrainTargetDto> trains;
    private String transferStation;
    private Integer seats;
}
