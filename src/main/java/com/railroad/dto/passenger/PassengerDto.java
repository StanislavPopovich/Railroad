package com.railroad.dto.passenger;

import com.railroad.validator.ValidDay;
import com.railroad.validator.ValidMonth;
import com.railroad.validator.ValidYear;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class PassengerDto {

    @NotNull(message = "{passengerLastName.notNull.error}")
    @Pattern(regexp = "^[a-zA-Z]+((['-][a-zA-Z ])?[a-zA-Z]*)*$", message = "{passengerLastName.pattern.error}")
    private String lastName;

    @NotNull(message = "{passengerName.notNull.error}")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "{passengerName.pattern.error}")
    private String name;

    @NotNull(message = "{passengerEmail.notNull.error}")
    @Pattern(regexp = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$",
            message = "{passengerEmail.pattern.error}")
    private String email;

    @NotNull(message = "{myBirthDate.notNull.error}")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$",
            message = "{myBirthDate.pattern.error}")
    @ValidYear(message = "{myBirthDate.year.error}")
    @ValidMonth(message = "{myBirthDate.month.error}")
    @ValidDay(message = "{myBirthDate.day.error}")
    private String birthDate;

    public PassengerDto(){}
    public PassengerDto(String lastName, String name, String birthDate){
        this.lastName = lastName;
        this.name = name;
        this.birthDate = birthDate;
    }
}
