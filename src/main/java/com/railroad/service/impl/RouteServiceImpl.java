package com.railroad.service.impl;

import com.railroad.dto.route.RouteDto;
import com.railroad.entity.WayEntity;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.mapper.RouteDtoMapper;
import com.railroad.service.api.RouteService;
import com.railroad.service.api.StationService;
import com.railroad.service.api.WayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stanislav Popovich
 */

@Service
public class RouteServiceImpl extends BaseService implements RouteService {

    private List<Integer> list;
    private boolean[] visited;
    private int size;
    private int[][] graph;

    @Autowired
    private StationService stationService;

    @Autowired
    private RouteDtoMapper routeDtoMapper;

    @Autowired
    private WayService wayService;

    /**
     * Getting all routes from departure station to arrival station
     * @param departStation departure station
     * @param arrivalStation arrival station
     * @return List of RouteDtos
     */
    @Override
    @Transactional
    public List<RouteDto> getAllRoutes(String departStation, String arrivalStation) throws RailroadDaoException {
        List<RouteDto> targetRoutes = new ArrayList<>();
        List<String> routes = getRoutes(departStation, arrivalStation);
        for(String route: routes){
            String[] stationsArr = route.split(",");
            List<String> stations = new ArrayList<>();
            for(int i = 0; i < stationsArr.length; i++){
                stations.add(stationService.getStationById(new Long(stationsArr[i])).getName());
            }
            targetRoutes.add(routeDtoMapper.stringToRoute(stations));
        }
        return targetRoutes;
    }

    /**
     * Getting all routes that represents in strings of id
     * @param departStation departure station
     * @param arrivalStation arrival station
     * @return List of Strings
     */
    private List<String> getRoutes(String departStation, String arrivalStation) throws RailroadDaoException {
        list = new ArrayList<>();
        int countStations = stationService.getIdOfLastStation();
        visited = new boolean[countStations + 1];
        graph = getSmegMatrix(wayService.getAll(), countStations);
        size = 0;
        List<String> allRoutes = dfs(stationService.getStationByName(departStation).getId().intValue(),
                stationService.getStationByName(arrivalStation).getId().intValue(), new ArrayList<>());
        return allRoutes;
    }

    /**
     * Getting all routes that represents in strings of id
     * @param departStationId
     * @param arrivalStationId
     * @param allRoutes
     * @return
     */
    private List<String> dfs(int departStationId, int arrivalStationId, List<String> allRoutes){
        //station added to path
        list.add(departStationId);
        size++;

        //station marked as visited
        visited[departStationId] = true;

        //when the destination station is found
        if(departStationId == arrivalStationId){
            StringBuffer sb = new StringBuffer();
            for(Integer station: list){
                sb.append(station + ",");
            }
            allRoutes.add(sb.toString());
        }

        for(int i = 1; i<= visited.length - 1; i++){
            // if there's an edge between  departStationId and i station
            if(graph[departStationId][i] == 1){
                //and that station is not visited yet
                if(visited[i] == false){
                    //start dfs from that station
                    allRoutes = dfs(i, arrivalStationId, allRoutes);
                    visited[i] = false;
                    size--;
                    list.remove(size);
                }
            }
        }
        return allRoutes;
    }

    /**
     * Getting smeg matrix
     * @param wayEntities list of wayEntities
     * @param countStation count of stations
     * @return array
     */
    private int[][] getSmegMatrix(List<WayEntity> wayEntities, int countStation){
        int[][] matrix = getEmptyMatrix(countStation + 1);
        for(WayEntity wayEntity : wayEntities){
            matrix[wayEntity.getFirstStationEntity().getId().intValue()][wayEntity.getSecondStationEntity().getId().intValue()] = 1;
            matrix[wayEntity.getSecondStationEntity().getId().intValue()][wayEntity.getFirstStationEntity().getId().intValue()] = 1;
        }
        return matrix;
    }
}
