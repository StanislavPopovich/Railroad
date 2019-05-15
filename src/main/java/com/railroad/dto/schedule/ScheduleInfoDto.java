package com.railroad.dto.schedule;

import lombok.Data;

import java.util.List;

/**
 * Data transfer object that represent schedule
 *
 * @author Stanislav Popovich
 */

@Data
public class ScheduleInfoDto {
    private String arrivalDate;
    private String departDate;
    private String departDateFromFirstStation;
    private Integer train;
    private String station;
    private List<String> stations;
}
