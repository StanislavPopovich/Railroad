package com.railroad.service.api;

import com.railroad.model.TrainEntity;

import java.util.Date;

public interface TicketService {

    Long getCountTicketsByTrainAndDate(TrainEntity train, Date departDate);
}
