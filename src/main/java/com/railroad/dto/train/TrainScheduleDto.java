package com.railroad.dto.train;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TrainScheduleDto {
    private Integer number;
    private String departDate;
    private String arrivalDate;
}
