package com.railroad.dto.train;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TrainTargetDto {
    private Integer number;
    private Integer seats;
    private String departDate;
    private String arrivalDate;
}
