package com.railroad.dto.schedule;

import lombok.Data;

import java.util.List;

/**
 * Data transfer object that represent schedule which sending to scoreboard
 *
 * @author Stanislav Popovich
 */

@Data
public class ScheduleMessageInfoDto {
    private String arrivalDate;
    private String departDate;
    private String departDateFromFirstStation;
    private Integer train;
    private String station;
    private List<String> stations;
}
