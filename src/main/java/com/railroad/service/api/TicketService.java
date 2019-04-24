package com.railroad.service.api;

import com.railroad.dto.TicketDto;
import com.railroad.model.*;

import java.util.Date;
import java.util.List;

public interface TicketService {

    void save(TicketEntity ticketEntity);
    Long getCountTicketByTrainAndSchedules(TrainEntity trainEntity, ScheduleEntity depart,
                                           ScheduleEntity arrival);
    List<TicketDto> getNotActualTickets(UserEntity currentUser);
    List<TicketDto> getActualTickets(UserEntity currentUser);
    void removeTicketByNumber(Long ticketNumber);
    List<TicketDto> getPassengerActualTickets(UserEntity userEntity, PassengerEntity passengerEntity);
    List<TicketDto> getPassengerNotActualTickets(UserEntity userEntity, PassengerEntity passengerEntity);
    List<TicketEntity> getTicketsByTrainAndDepartDate(TrainEntity trainEntity, Date departDate);
}
