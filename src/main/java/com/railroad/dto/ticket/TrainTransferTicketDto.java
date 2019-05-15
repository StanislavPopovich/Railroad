package com.railroad.dto.ticket;

import lombok.Data;

/**
 * Data transfer object that represent ticket for two trains
 *
 * @author Stanislav Popovich
 */

@Data
public class TrainTransferTicketDto {
    private TrainTicketDto firstTrain;
    private TrainTicketDto secondTrain;
}
