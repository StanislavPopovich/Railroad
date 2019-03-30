package com.railroad.dao.api;

import com.railroad.model.TicketEntity;

import java.util.List;

/**
 * DAO for the {@link TicketEntity} objects.
 *
 * @author Stanislav Popovich
 */
public interface TicketGenericDao extends GenericDao<TicketEntity, Long> {

    /**
     * Method for finding TicketEntities in DB by trainId
     * @param trainId
     * @return list of entities from DB
     */
    List<TicketEntity> getTicketsByTrainId(Long trainId);
}
