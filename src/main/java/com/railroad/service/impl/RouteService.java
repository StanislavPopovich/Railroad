package com.railroad.service.impl;

import com.railroad.dto.route.RouteDto;
import com.railroad.entity.WayEntity;
import com.railroad.mapper.RouteDtoMapper;
import com.railroad.service.api.StationService;
import com.railroad.service.api.WayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteService extends BaseService {

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

    @Transactional
    public List<RouteDto> getAllRoutes(String departStation, String arrivalStation) {
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

    private List<String> getRoutes(String startStation, String endStation) {
        list = new ArrayList<>();
        int countStations = stationService.getIdOfLastStation();
        visited = new boolean[countStations + 1];
        graph = getSmegMatrix(wayService.getAll(), countStations);
        size = 0;
        List<String> allRoutes = dfs(stationService.getStationEntityByStationName(startStation).getId().intValue(),
                stationService.getStationEntityByStationName(endStation).getId().intValue(), new ArrayList<>());
        return allRoutes;
    }

    private List<String> dfs(int startStationId, int endStationId, List<String> allRoutes){
        //station added to path
        list.add(startStationId);
        size++;

        //station marked as visited
        visited[startStationId] = true;

        //when the destination station is found
        if(startStationId == endStationId){
            StringBuffer sb = new StringBuffer();
            for(Integer station: list){
                sb.append(station + ",");
            }
            allRoutes.add(sb.toString());
        }

        for(int i = 1; i<= visited.length - 1; i++){
            // if there's an edge between  startStationId and i station
            if(graph[startStationId][i] == 1){
                //and that station is not visited yet
                if(visited[i] == false){
                    //start dfs from that station
                    allRoutes = dfs(i, endStationId, allRoutes);
                    visited[i] = false;
                    size--;
                    list.remove(size);
                }
            }
        }
        return allRoutes;
    }

    private int[][] getSmegMatrix(List<WayEntity> wayEntities, int countStation){
        int[][] matrix = getEmptyMatrix(countStation + 1);
        for(WayEntity wayEntity : wayEntities){
            matrix[wayEntity.getFirstStationEntity().getId().intValue()][wayEntity.getSecondStationEntity().getId().intValue()] = 1;
            matrix[wayEntity.getSecondStationEntity().getId().intValue()][wayEntity.getFirstStationEntity().getId().intValue()] = 1;
        }
        return matrix;
    }
}
