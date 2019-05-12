package com.railroad.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BirthYear implements ConstraintValidator<ValidBirthYear, String> {

    @Override
    public void initialize(final ValidBirthYear annotation) {
    }

    @Override
    public boolean isValid(final String birthDate, final ConstraintValidatorContext constraintValidatorContext) {
        String[] date = birthDate.split("-");
        return validYear(Integer.parseInt(date[0]));
    }

    private boolean validYear(final Integer year){
        if(year < 1900 || year > 2019){
            return false;
        }
        return true;
    }


}
