package com.railroad.mapper;

import com.railroad.dto.WayDto;
import com.railroad.model.Way;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WayDtoMapper {

    @Mapping(source = "firstStationId", target = "firstStationId")
    @Mapping(source = "secondStationId", target = "secondStationId")
    @Mapping(source = "distance", target = "distance")
    Way wayDtoToWay(WayDto wayDto);

    @Mapping(source = "firstStationId", target = "firstStationId")
    @Mapping(source = "secondStationId", target = "secondStationId")
    @Mapping(source = "distance", target = "distance")
    WayDto wayToWayDto(Way way);

}
