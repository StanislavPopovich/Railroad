package com.railroad.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class YearValidator implements ConstraintValidator<ValidYear, String> {

    @Override
    public void initialize(final ValidYear annotation) {
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
