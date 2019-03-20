package com.railroad.mapper.api;

import com.railroad.dto.WayDto;
import com.railroad.model.Way;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WayDtoMapper {
    Way wayDtoToWay(WayDto wayDto);
    WayDto wayToWayDto(Way way);
    List<WayDto> waysToWayDtos(List<Way> ways);
}
