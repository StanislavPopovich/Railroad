package com.railroad.service.api;

import com.railroad.model.ScheduleEntity;
import com.railroad.model.TicketEntity;
import com.railroad.model.TrainEntity;

import java.util.Date;

public interface TicketService {

    Long getCountTicketsByTrainAndDate(TrainEntity train, Date departDate);
    void saveTicket(TicketEntity ticketEntity);
    public Long getCountTicketByTrainAndSchedeles(TrainEntity trainEntity, ScheduleEntity depart,
                                                  ScheduleEntity arrival);
}
