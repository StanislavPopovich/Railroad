package com.railroad.dto.way;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class WayDto {
    @NotNull(message = "{station.notNull.error}")
    @Size(min = 1, message = "{station.size.error}")
    @Pattern(regexp = "^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$", message = "{station.pattern.error}")
    private String firstStation;

    @NotNull(message = "{station.notNull.error}")
    @Size(min = 1, message = "{station.size.error}")
    @Pattern(regexp = "^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$", message = "{station.pattern.error}")
    private String secondStation;
}
