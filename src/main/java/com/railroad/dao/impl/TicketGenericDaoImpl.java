package com.railroad.dao.impl;

import com.railroad.dao.api.TicketGenericDao;
import com.railroad.model.TicketEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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

    @SuppressWarnings("unchecked")
    @Override
    public List<TicketEntity> getTicketsByTrainId(Long trainId) throws EntityNotFoundException {
        Session session = getSession();
        List<TicketEntity> tickets = session.createQuery("select t from " +
                "TicketEntity t where t.train_id=:train_id ").setParameter("train_id", trainId).list();
        if(tickets.size() == 0){
            throw new EntityNotFoundException("not found any tickets");
        }
        return tickets;
    }
}
