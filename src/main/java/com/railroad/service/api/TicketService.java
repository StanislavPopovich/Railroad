package com.railroad.service.api;

import com.railroad.dto.TicketDto;
import com.railroad.model.ScheduleEntity;
import com.railroad.model.TicketEntity;
import com.railroad.model.TrainEntity;
import com.railroad.model.UserEntity;

import java.util.Date;
import java.util.List;

public interface TicketService {

    void saveTicket(TicketEntity ticketEntity);
    Long getCountTicketByTrainAndSchedules(TrainEntity trainEntity, ScheduleEntity depart,
                                           ScheduleEntity arrival);
    List<TicketDto> getAllTickets(UserEntity currentUser);
    List<TicketDto> getActualTickets(UserEntity currentUser);
}
