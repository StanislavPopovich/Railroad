package com.railroad.dto.train;

import lombok.Data;

@Data
public class TrainTransferTicketDto {
    private TrainTicketDto firstTrain;
    private TrainTicketDto secondTrain;
    /*private boolean isExistSecondTrain;*/
}
