package com.railroad.dto;

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
    private List<String> stations;
}
