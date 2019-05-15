package com.railroad.dto.route;

import lombok.Data;

import java.util.List;

/**
 * Data transfer object that represent route
 *
 * @author Stanislav Popovich
 */

@Data
public class RouteDto {
    List<String> stations;
}
