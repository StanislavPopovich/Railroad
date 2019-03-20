package com.railroad.service.impl;

import com.railroad.dao.api.StationGenericDao;
import com.railroad.dao.api.WayGenericDao;
import com.railroad.dto.WayDto;
import com.railroad.mapper.api.WayDtoMapper;
import com.railroad.model.Way;
import com.railroad.service.api.WayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WayServiceImpl implements WayService {

    private List<Integer> list;
    private boolean[] visited;
    private int size;
    private int[][] graph;

    @Autowired
    private WayGenericDao wayGenericDao;

    @Autowired
    private StationGenericDao stationGenericDao;

    @Autowired
    private WayDtoMapper wayDtoMapper;

    @Override
    public void save(WayDto wayDto) {
        wayGenericDao.save(wayDtoMapper.wayDtoToWay(wayDto));

    }

    @Override
    public List<WayDto> getAll() {
              return wayDtoMapper.waysToWayDtos(wayGenericDao.getAll());
    }

    @Override
    public List<String> getAllRoutes(String startStation, String endStation) {
        list = new ArrayList<>();
        visited = new boolean[stationGenericDao.getAll().size() + 1];
        graph = getSmegMatrix(wayGenericDao.getAll());
        size = 0;
        List<String> allRoutes = dfs(stationGenericDao.findByStationName(startStation).getId().intValue(),
                stationGenericDao.findByStationName(endStation).getId().intValue(), new ArrayList<>());
        return allRoutes;
    }

    /**
     * The method returns all routes from the starting station to the end station
     * @param startStationId
     * @param endStationId
     * @param allRoutes
     * @return
     */
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

    private int[][] getSmegMatrix(List<Way> ways){
        int[][] matrix = getEmptyMatrix(ways.size());
        for(Way way:ways){
            matrix[way.getFirstStation().getId().intValue()][way.getSecondStation().getId().intValue()] = 1;
            matrix[way.getSecondStation().getId().intValue()][way.getFirstStation().getId().intValue()] = 1;
        }
        return matrix;
    }

    private int[][] getEmptyMatrix(int size){
        int[][] matrix = new int[size][size];
        for(int row = 0; row< matrix.length; row++){
            for(int column = 0; column < matrix[row].length; column++){
                matrix[row][column] = 0;
            }
        }
        return matrix;
    }
}
