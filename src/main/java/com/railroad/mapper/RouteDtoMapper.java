package com.railroad.mapper;

import com.railroad.dto.RouteDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RouteDtoMapper {

    default RouteDto stringToRoute(List<String> stations){
        if(stations == null){
            return null;
        }else {
            RouteDto routeDto = new RouteDto();
            List<String> routeStations = new ArrayList<>();
            for(String station: stations){
                routeStations.add(station);
            }
            routeDto.setStations(stations);
            return routeDto;
        }
    }
}
