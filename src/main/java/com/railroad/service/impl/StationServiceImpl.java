package com.railroad.service.impl;

import com.railroad.dao.impl.StationGenericDaoImpl;
import com.railroad.dto.StationDto;
import com.railroad.mapper.StationDtoMapper;
import com.railroad.model.Station;
import com.railroad.service.api.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationGenericDaoImpl stationGenericDao;

    @Autowired
    private StationDtoMapper stationDtoMapper;

    @Override
    public void save(StationDto stationDto) {
        Station station = stationDtoMapper.StationDtoToStation(stationDto);
        stationGenericDao.save(station);
    }

    @Override
    public List<Station> getAll() {
        return stationGenericDao.getAll();
    }

    @Override
    public Station getStationByName(StationDto stationDto) {
        return stationGenericDao.findByStationName(stationDto.getName());
    }
}
