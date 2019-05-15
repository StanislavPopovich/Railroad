package com.railroad.dto.ticket;

import lombok.Data;

/**
 * Data transfer object that represent ticket for two direction
 *
 * @author Stanislav Popovich
 */

@Data
public class GlobalTrainsTicketDto {
    private TrainTransferTicketDto toTrain;
    private TrainTransferTicketDto returnTrain;
}
