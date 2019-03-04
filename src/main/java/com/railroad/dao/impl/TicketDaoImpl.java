package com.railroad.dao.impl;

import com.railroad.dao.TicketDao;
import com.railroad.model.Ticket;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl extends BaseDao<Ticket, Long> implements TicketDao {

    public TicketDaoImpl() {
        super(Ticket.class);
    }
}
