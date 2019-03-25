package com.railroad.dao.impl;

import com.railroad.dao.api.PassengerGenericDao;
import com.railroad.model.PassengerEntity;
import org.springframework.stereotype.Repository;

/**
 * DAO implementation for the {@link PassengerEntity} objects.
 *
 * @author Stanislav Popovich
 */
@Repository
public class PassengerGenericDaoImpl extends BaseGenericDao<PassengerEntity, Long> implements PassengerGenericDao {

    public PassengerGenericDaoImpl() {
        super(PassengerEntity.class);
    }

}
