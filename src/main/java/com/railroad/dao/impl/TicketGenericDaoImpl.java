package com.railroad.dao.impl;

import com.railroad.dao.api.TicketGenericDao;
import com.railroad.model.*;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @SuppressWarnings("unchecked")
    @Override
    public List<TicketEntity> getActualTicketsForPassenger(UserEntity userEntity, PassengerEntity passengerEntity) {
        List<TicketEntity> tickets = entityManager.createQuery("select t from TicketEntity t " +
                "where t.userEntity= :userEntity and t.passengerEntity= :passengerEntity and " +
                "t.departDate.departDate > :currentDate order by t.departDate").
                setParameter("userEntity" , userEntity).
                setParameter("currentDate", getCurrentDate()).
                setParameter("passengerEntity", passengerEntity).
                getResultList();
        return tickets;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TicketEntity> getNotActualTicketsForPassenger(UserEntity userEntity, PassengerEntity passengerEntity) {
        List<TicketEntity> tickets = entityManager.createQuery("select t from TicketEntity t " +
                "where t.userEntity= :userEntity and t.passengerEntity= :passengerEntity and " +
                "t.departDate.departDate < :currentDate order by t.departDate").
                setParameter("userEntity" , userEntity).
                setParameter("currentDate", getCurrentDate()).
                setParameter("passengerEntity", passengerEntity).
                getResultList();
        return tickets;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TicketEntity> getActualTickets(UserEntity userEntity) {
        List<TicketEntity> tickets = entityManager.createQuery("select t from TicketEntity t " +
                "where t.userEntity= :userEntity and t.departDate.departDate > :currentDate order by t.departDate").
                setParameter("userEntity" , userEntity).
                setParameter("currentDate", getCurrentDate()).getResultList();
        return tickets;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TicketEntity> getNotActualTickets(UserEntity userEntity) {
        List<TicketEntity> tickets = entityManager.createQuery("select t from TicketEntity t " +
                "where t.userEntity= :userEntity and t.departDate.departDate < :currentDate order by t.departDate").
                setParameter("userEntity" , userEntity).
                setParameter("currentDate", getCurrentDate()).getResultList();
        return tickets;
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

    @Override
    public void removeTicketByNumber(Long ticketNumber) {
        entityManager.createQuery("delete from TicketEntity t where t.id= :ticketNumber").
                setParameter("ticketNumber", ticketNumber).executeUpdate();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<TicketEntity> getTicketsByTrainAndDepartDate(TrainEntity trainEntity, Date departDate) {
        List<TicketEntity> tickets = entityManager.createQuery("select t from TicketEntity t " +
                "where t.trainEntity= :trainEntity and t.departDate.departDateFromFirstStation= :departDate").
                setParameter("trainEntity" , trainEntity).
                setParameter("departDate", departDate).getResultList();
        return tickets;
    }


}
