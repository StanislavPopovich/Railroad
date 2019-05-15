package com.railroad.service.impl;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.dto.station.StationDto;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.mapper.StationEntityDtoMapper;
import com.railroad.entity.StationEntity;
import com.railroad.service.api.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Stanislav Popovich
 */
@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationGenericDao stationGenericDao;

    @Autowired
    private StationEntityDtoMapper stationDtoMapper;

    /**
     * Saving a new entity to database
     * @param stationDto station data transfer object
     */
    @Override
    @Transactional
    public void save(StationDto stationDto) throws RailroadDaoException {
        StationEntity stationEntity = stationDtoMapper.stationDtoToStationEntity(stationDto);
        stationGenericDao.save(stationEntity);
    }

    /**
     * Getting list data transfer objects of all stations
     * @return List of StationDto
     */
    @Override
    public List<StationDto> getAll() throws RailroadDaoException {
        return stationDtoMapper.stationEntitiesToStationDtos(stationGenericDao.getAll());
    }

    /**
     * Getting list names of all stations
     * @return List of Strings
     */
    @Override
    public List<String> getAllStationsName() throws RailroadDaoException {
        return stationGenericDao.getAllStationNames();
    }

    /**
     * Getting list names of all stations without departure station
     * @param departStation departure station
     * @return List of Strings
     */
    @Override
    public List<String> getAllStationsNameWithoutDepartStation(String departStation) throws RailroadDaoException {
        List<String> list = new ArrayList<>();
        for(String station: getAllStationsName()){
            if(!station.equals(departStation)){
                list.add(station);
            }
        }
        return list;
    }

    /**
     * Getting station by name
     * @param name name of station
     * @return StationEntity from database
     */
    @Override
    public StationEntity getStationByName(String name) throws RailroadDaoException {
        return stationGenericDao.findByStationName(name);
    }


    /**
     * Getting station by id
     * @param id station's id
     * @return StationDto
     */
    @Override
    public StationDto getStationById(Long id) throws RailroadDaoException {
        StationEntity stationEntity = stationGenericDao.getById(id);
        return stationDtoMapper.stationEntityToStationDto(stationEntity);
    }

    /**
     * Getting last existing id from database
     * @return int
     */
    @Override
    public int getIdOfLastStation() throws RailroadDaoException {
        return stationGenericDao.getIdOfLastStation();
    }

    /**
     * Checking exist station in database by name
     * @param name station's name
     * @return true or false
     */
    @Override
    public boolean isAlreadyExist(String name) throws RailroadDaoException {
        if(stationGenericDao.getCountStations(name) > 0){
            return true;
        }
        return false;
    }

}
