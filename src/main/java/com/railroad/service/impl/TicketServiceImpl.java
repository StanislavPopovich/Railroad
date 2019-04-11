package com.railroad.service.impl;

import com.railroad.dao.api.TicketGenericDao;
import com.railroad.model.ScheduleEntity;
import com.railroad.model.TicketEntity;
import com.railroad.model.TrainEntity;
import com.railroad.service.api.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketGenericDao ticketDao;

    @Override
    public Long getCountTicketsByTrainAndDate(TrainEntity train, Date departDate) {
        return ticketDao.getCountTicketsByTrain(train, departDate);
    }

    @Transactional
    @Override
    //+
    public void saveTicket(TicketEntity ticketEntity) {
        ticketDao.save(ticketEntity);
    }

    public Long getCountTicketByTrainAndSchedeles(TrainEntity trainEntity, ScheduleEntity depart, ScheduleEntity arrival){
        return ticketDao.getCountTicketsByTrainAndSchedules(trainEntity, depart, arrival);
    }
}
