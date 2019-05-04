package com.railroad.dto.train;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
public class TrainTicketDto {
    private Integer number;
    private String departDate;
    private String arrivalDate;
    private String departStation;
    private String arrivalStation;
}
