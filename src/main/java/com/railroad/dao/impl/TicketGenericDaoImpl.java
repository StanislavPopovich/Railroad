package com.railroad.dao.impl;

import com.railroad.dao.api.TicketGenericDao;
import com.railroad.model.TicketEntity;
import org.springframework.stereotype.Repository;

/**
 * DAO implementation for the {@link TicketEntity} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class TicketGenericDaoImpl extends BaseGenericDao<TicketEntity, Long> implements TicketGenericDao {

    public TicketGenericDaoImpl() {
        super(TicketEntity.class);
    }
}
