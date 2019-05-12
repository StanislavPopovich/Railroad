package com.railroad.dto.train;

import lombok.Data;

@Data
public class GlobalTrainsTicketDto {
    private TrainTransferTicketDto toTrain;
    private TrainTransferTicketDto returnTrain;
    /*private boolean isReturnTrainExist;*/
}
