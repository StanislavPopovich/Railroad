package com.railroad.dto.schedule;

import lombok.Data;

import java.util.List;

/**
 * Data transfer object that represent schedule for train which will be updating
 *
 * @author Stanislav Popovich
 */

@Data
public class ScheduleUpdateDto {
    private String oldDepartDateFromFirstStation;
    private Integer number;
    private List<String> stations;
    private List<String> arrivalDates;
    private List<String> departDates;
}
