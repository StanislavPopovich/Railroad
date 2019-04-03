package com.railroad.dao.impl;

import com.railroad.dao.api.TicketGenericDao;
import com.railroad.model.TicketEntity;
import com.railroad.model.TrainEntity;
import org.hibernate.Session;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Date;
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
    public Long getCountTicketsByTrain(TrainEntity train, Date departDate){
        Session session = getSession();
        Long count = (Long) session.createQuery("select count(t) from " +
                "TicketEntity t where t.trainEntity= :train and t.startData.departDate > :departDate " +
                "and t.startData.departDate < :afterDate").setParameter("train", train).
                setParameter("departDate", departDate).
                setParameter("afterDate", new LocalDate(departDate).plusDays(1).toDate()).uniqueResult();
        return count;
    }
}
