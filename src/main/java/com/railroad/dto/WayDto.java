package com.railroad.dto;

import lombok.Data;

@Data
public class WayDto {
    private Long firstStationId;
    private Long secondStationId;
    private Double distance;
}
