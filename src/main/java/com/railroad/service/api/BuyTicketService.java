package com.railroad.service.api;

import com.railroad.dto.passenger.PassengerDto;
import com.railroad.dto.ticket.GlobalTrainsTicketDto;
import com.railroad.exceptions.RailroadDaoException;

/**
 * @author Stanislav Popovich
 */

public interface BuyTicketService {

    void saveTicket(GlobalTrainsTicketDto globalTrainsTicketDto, PassengerDto passengerDto) throws RailroadDaoException;

}
