package com.railroad.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TicketDto {
    private TrainDto trainDto;
    private PassengerDto passengerDto;

    public TicketDto(){}

    public TicketDto(TrainDto trainDto, PassengerDto passengerDto){
        this.trainDto = trainDto;
        this.passengerDto = passengerDto;
    }
}
