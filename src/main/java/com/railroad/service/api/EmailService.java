package com.railroad.service.api;

import com.railroad.dto.ticket.TicketDto;

import java.util.List;

/**
 * @author Stanislav Popovich
 */

public interface EmailService {

    void sendMail(List<TicketDto> tickets, String typeEmail);
}
