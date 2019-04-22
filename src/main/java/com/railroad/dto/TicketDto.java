package com.railroad.dto;

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
    private String email;

    public TicketDto(){}

    public TicketDto(TrainTicketDto trainTicketDto, PassengerDto passengerDto){
        this.trainTicketDto = trainTicketDto;
        this.passengerDto = passengerDto;
    }
}
