package com.railroad.service.api;

import com.railroad.dto.StationDto;

import java.util.List;

/**
 * Service interface for {@link com.railroad.model.StationEntity}
 * @author Stanislav Popovich
 * @version 1.0
 */
public interface StationService {

    /**
     * The method sends stationDto to dao layer
     * @param stationDto
     */
    void save(StationDto stationDto);

    /**
     * The method returns stationDtos from dao layer
     * @return list StationDto
     */
    List<StationDto> getAll();

    /**
     * The method returns stations names from dao layer
     * @return list of Strings
     */
    List<String> getAllStationsName();

    /**
     * The method return stationDto by name drom dao layer
     * @param stationName
     * @return StationDto
     */
    StationDto getStationByName(String stationName);

    /**
     * The method returns stationDto by id from dao layer
     * @param id
     * @return StationDto
     */
    StationDto getStationById(Long id);

}
