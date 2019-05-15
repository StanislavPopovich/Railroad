package com.railroad.dto.ticket;

import lombok.Data;

/**
 * Data transfer object that represent ticket for one train
 *
 * @author Stanislav Popovich
 */

@Data
public class TrainTicketDto {
    private Integer number;
    private String departDate;
    private String arrivalDate;
    private String departStation;
    private String arrivalStation;
}
