package com.railroad.dto.ticket;

import com.railroad.dto.passenger.PassengerDto;
import lombok.Data;

/**
 * Data transfer object that represent TicketEntity
 *
 * @author Stanislav Popovich
 */

@Data
public class TicketDto {
    private TrainTicketDto trainTicketDto;
    private PassengerDto passengerDto;
    private Long number;

    public TicketDto(){}

    public TicketDto(TrainTicketDto trainTicketDto, PassengerDto passengerDto){
        this.trainTicketDto = trainTicketDto;
        this.passengerDto = passengerDto;
    }
}
