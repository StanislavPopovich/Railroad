package com.railroad.service.impl;

import com.railroad.dao.api.TicketGenericDao;
import com.railroad.model.TrainEntity;
import com.railroad.service.api.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketGenericDao ticketDao;

    @Override
    public Long getCountTicketsByTrainAndDate(TrainEntity train, Date departDate) {
        return ticketDao.getCountTicketsByTrain(train, departDate);
    }
}
