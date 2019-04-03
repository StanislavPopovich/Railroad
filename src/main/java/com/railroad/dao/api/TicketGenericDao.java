package com.railroad.dao.api;

import com.railroad.model.TicketEntity;
import com.railroad.model.TrainEntity;

import java.util.Date;
import java.util.List;

/**
 * DAO for the {@link TicketEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface TicketGenericDao extends GenericDao<TicketEntity, Long> {


    Long getCountTicketsByTrain(TrainEntity train, Date departDate);
}
