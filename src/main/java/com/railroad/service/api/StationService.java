package com.railroad.service.api;

import com.railroad.dto.station.StationDto;
import com.railroad.entity.StationEntity;
import com.railroad.exceptions.RailroadDaoException;

import java.util.List;

/**
 * @author Stanislav Popovich
 */
public interface StationService {

    void save(StationDto stationDto) throws RailroadDaoException;

    List<StationDto> getAll() throws RailroadDaoException;

    List<String> getAllStationsName() throws RailroadDaoException;

    List<String> getAllStationsNameWithoutDepartStation(String departStation) throws RailroadDaoException;

    StationEntity getStationByName(String name) throws RailroadDaoException;

    StationDto getStationById(Long id) throws RailroadDaoException;

    int getIdOfLastStation() throws RailroadDaoException;

    boolean isAlreadyExist(String name) throws RailroadDaoException;

}
