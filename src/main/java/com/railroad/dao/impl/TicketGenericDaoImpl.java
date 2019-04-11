package com.railroad.dao.impl;

import com.railroad.dao.api.TicketGenericDao;
import com.railroad.model.ScheduleEntity;
import com.railroad.model.TicketEntity;
import com.railroad.model.TrainEntity;
import org.hibernate.Session;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext
    private EntityManager entityManager;

    public TicketGenericDaoImpl() {
        super(TicketEntity.class);
    }

    @Override
    public Long getCountTicketsByTrain(TrainEntity train, Date departDate){
        Long count = (Long) entityManager.createQuery("select count(t) from " +
                "TicketEntity t where t.trainEntity= :train and t.departDate.departDate > :departDate " +
                "and t.departDate.departDate < :afterDate").setParameter("train", train).
                setParameter("departDate", departDate).
                setParameter("afterDate", new LocalDate(departDate).plusDays(1).toDate()).getSingleResult();
        return count;
    }

    @Override
    public Long getCountTicketsByTrainAndSchedules(TrainEntity trainEntity, ScheduleEntity depart, ScheduleEntity arrival){
        Long count = (Long) entityManager.createQuery("select count(t) from " +
                "TicketEntity t where t.trainEntity= :train and t.departDate= :departDate " +
                "and t.arrivalDate= :arrivalDate").setParameter("train", trainEntity).
                setParameter("departDate", depart).
                setParameter("arrivalDate", arrival).getSingleResult();
        return count;
    }
}
