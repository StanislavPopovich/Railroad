package com.railroad.dto.ticket;

import com.railroad.dto.train.TrainTicketDto;
import com.railroad.dto.passenger.PassengerDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
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
