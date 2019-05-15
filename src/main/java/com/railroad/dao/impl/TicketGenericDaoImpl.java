package com.railroad.dao.impl;

import com.railroad.dao.api.TicketGenericDao;
import com.railroad.entity.*;
import com.railroad.exceptions.RailroadDaoException;
import org.apache.log4j.Logger;
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

    private static final org.apache.log4j.Logger logger = Logger.getLogger(TicketGenericDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public TicketGenericDaoImpl() {
        super(TicketEntity.class);
    }

    /**
     * Getting actual tickets for passenger by UserEntity account
     * @param userEntity user account
     * @param passengerEntity passenger
     * @return List of TicketEntities
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TicketEntity> getActualTicketsForPassenger(UserEntity userEntity, PassengerEntity passengerEntity) throws RailroadDaoException {
        List<TicketEntity> tickets;
        try{
            tickets = entityManager.createQuery("select t from TicketEntity t " +
                    "where t.userEntity= :userEntity and t.passengerEntity= :passengerEntity and " +
                    "t.departDate.departDate > :currentDate order by t.departDate").
                    setParameter("userEntity" , userEntity).
                    setParameter("currentDate", getCurrentDate()).
                    setParameter("passengerEntity", passengerEntity).
                    getResultList();
        }catch (Exception e){
            logger.warn("Exception in TicketGenericDaoImpl - getActualTicketsForPassenger().");
            throw new RailroadDaoException(e);
        }
        return tickets;
    }

    /**
     * Getting not actual tickets for passenger by UserEntity account
     * @param userEntity user account
     * @param passengerEntity passenger
     * @return List of TicketEntities
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TicketEntity> getNotActualTicketsForPassenger(UserEntity userEntity, PassengerEntity passengerEntity)
            throws RailroadDaoException {
        List<TicketEntity> tickets;
        try{
            tickets = entityManager.createQuery("select t from TicketEntity t " +
                    "where t.userEntity= :userEntity and t.passengerEntity= :passengerEntity and " +
                    "t.departDate.departDate < :currentDate order by t.departDate").
                    setParameter("userEntity" , userEntity).
                    setParameter("currentDate", getCurrentDate()).
                    setParameter("passengerEntity", passengerEntity).
                    getResultList();
        }catch (Exception e){
            logger.warn("Exception in TicketGenericDaoImpl - getNotActualTicketsForPassenger().");
            throw new RailroadDaoException(e);
        }
        return tickets;
    }

    /**
     * Getting actual tickets for user account
     * @param userEntity user account
     * @return List of TicketEntities
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TicketEntity> getActualTickets(UserEntity userEntity) throws RailroadDaoException {
        List<TicketEntity> tickets;
        try{
            tickets = entityManager.createQuery("select t from TicketEntity t " +
                    "where t.userEntity= :userEntity and t.departDate.departDate > :currentDate " +
                    "order by t.departDate.departDate").setParameter("userEntity" , userEntity).
                    setParameter("currentDate", getCurrentDate()).getResultList();
        }catch (Exception e){
            logger.warn("Exception in TicketGenericDaoImpl - getActualTickets().");
            throw new RailroadDaoException(e);
        }
        return tickets;
    }

    /**
     * Getting not actual tickets for user account
     * @param userEntity user account
     * @return List of TicketEntities
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TicketEntity> getNotActualTickets(UserEntity userEntity) throws RailroadDaoException {
        List<TicketEntity> tickets;
        try{
            tickets = entityManager.createQuery("select t from TicketEntity t " +
                    "where t.userEntity= :userEntity and t.departDate.departDate < :currentDate order by t.departDate").
                    setParameter("userEntity" , userEntity).
                    setParameter("currentDate", getCurrentDate()).getResultList();
        }catch (Exception e){
            logger.warn("Exception in TicketGenericDaoImpl - getNotActualTickets().");
            throw new RailroadDaoException(e);
        }
        return tickets;
    }

    /**
     * Getting count tickets for train on specific schedule
     * @param trainEntity train
     * @param depart schedule which contain departure date
     * @param arrival schedule which contain arrival date
     * @return Long
     */
    @Override
    public Long getCountTicketsByTrainAndSchedules(TrainEntity trainEntity, ScheduleEntity depart,
                                                   ScheduleEntity arrival) throws RailroadDaoException {
        Long count;
        try{
            count = (Long) entityManager.createQuery("select count(t) from " +
                    "TicketEntity t where t.trainEntity= :train and t.departDate= :departDate " +
                    "and t.arrivalDate= :arrivalDate").setParameter("train", trainEntity).
                    setParameter("departDate", depart).
                    setParameter("arrivalDate", arrival).getSingleResult();
        }catch (Exception e){
            logger.warn("Exception in TicketGenericDaoImpl - getCountTicketsByTrainAndSchedules().");
            throw new RailroadDaoException(e);
        }
        return count;
    }

    /**
     * Removing ticket
     * @param ticketNumber ticket id
     */
    @Override
    public void removeTicketByNumber(Long ticketNumber) throws RailroadDaoException {
        try{
            entityManager.createQuery("delete from TicketEntity t where t.id= :ticketNumber").
                    setParameter("ticketNumber", ticketNumber).executeUpdate();
        }catch (Exception e){
            logger.warn("Exception in TicketGenericDaoImpl - removeTicketByNumber().");
            throw new RailroadDaoException(e);
        }
    }

    /**
     * Getting count tickets for train on specific schedule
     * @param trainEntity train
     * @param departDate departure date
     * @return List of TicketEntities
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TicketEntity> getTicketsByTrainAndDepartDate(TrainEntity trainEntity, Date departDate)
            throws RailroadDaoException {
        List<TicketEntity> tickets;
        try{
            tickets = entityManager.createQuery("select t from TicketEntity t " +
                    "where t.trainEntity= :trainEntity and t.departDate.departDateFromFirstStation= :departDate").
                    setParameter("trainEntity" , trainEntity).
                    setParameter("departDate", departDate).getResultList();
        }catch (Exception e){
            logger.warn("Exception in TicketGenericDaoImpl - getTicketsByTrainAndDepartDate().");
            throw new RailroadDaoException(e);
        }
        return tickets;
    }

    /**
     * Getting passengers which have bought tickets on train
     * @param trainEntity train
     * @param departDate departure date
     * @return List of PassengerEntity
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<PassengerEntity> getTrainPassengers(TrainEntity trainEntity, Date departDate) throws RailroadDaoException {
        List<PassengerEntity> passengers;
        try{
            passengers = entityManager.createQuery("select t.passengerEntity from " +
                    "TicketEntity t where t.trainEntity= :train and t.departDate.departDateFromFirstStation= :date " +
                    "order by t.passengerEntity.lastName").
                    setParameter("train", trainEntity).
                    setParameter("date", departDate).getResultList();
        }catch (Exception e){
            logger.warn("Exception in TicketGenericDaoImpl - getTrainPassengers().");
            throw new RailroadDaoException(e);
        }
        return passengers;
    }

    /**
     *
     * @param passengerEntity
     * @param trainEntity
     * @param departDate
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public Long isPassengerBoughtTicket(PassengerEntity passengerEntity, TrainEntity trainEntity,
                                        Date departDate) throws RailroadDaoException {
        Long count;
        try{
            count = (Long) entityManager.createQuery("select count(t) from " +
                    "TicketEntity t where t.trainEntity= :train and t.passengerEntity= :passenger " +
                    "and t.departDate.departDateFromFirstStation= :departDate").setParameter("train", trainEntity).
                    setParameter("passenger", passengerEntity).
                    setParameter("departDate", departDate).getSingleResult();
        }catch (Exception e){
            logger.warn("Exception in TicketGenericDaoImpl - isPassengerBoughtTicket().");
            throw new RailroadDaoException(e);
        }
        return count;
    }

    /**
     * Getting id
     * @param ticket ticket
     * @return Long
     */
    @Override
    public Long getIdByTicket(TicketEntity ticket) throws RailroadDaoException {
        Long id;
        try{
            id = (Long) entityManager.createQuery("select t.id from TicketEntity t where t.trainEntity= " +
                    ":train and t.passengerEntity= :passenger and t.departDate= :departDate and t.arrivalDate= " +
                    ":arrivalDate and t.userEntity= :user").setParameter("train", ticket.getTrainEntity()).
                    setParameter("passenger", ticket.getPassengerEntity()).
                    setParameter("departDate", ticket.getDepartDate()).
                    setParameter("arrivalDate", ticket.getArrivalDate()).
                    setParameter("user", ticket.getUserEntity()).getSingleResult();
        }catch (Exception e){
            logger.warn("Exception in TicketGenericDaoImpl - getIdByTicket().");
            throw new RailroadDaoException(e);
        }
        return id;
    }


}
