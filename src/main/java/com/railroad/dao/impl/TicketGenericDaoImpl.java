package com.railroad.dao.impl;

import com.railroad.dao.api.TicketGenericDao;
import com.railroad.model.Ticket;
import org.springframework.stereotype.Repository;

@Repository
public class TicketGenericDaoImpl extends BaseGenericDao<Ticket, Long> implements TicketGenericDao {

    public TicketGenericDaoImpl() {
        super(Ticket.class);
    }
}
