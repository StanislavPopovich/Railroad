package com.railroad.service.api;

import com.railroad.dto.route.RouteDto;
import com.railroad.exceptions.RailroadDaoException;

import java.util.List;

/**
 * @author Stanislav Popovich
 */
public interface RouteService {

    List<RouteDto> getAllRoutes(String departStation, String arrivalStation) throws RailroadDaoException;
}
