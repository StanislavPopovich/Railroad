package com.railroad.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BirthMonth implements ConstraintValidator<ValidBirthMonth, String> {
    @Override
    public void initialize(final ValidBirthMonth annotation) {

    }

    @Override
    public boolean isValid(final String birthDate, final ConstraintValidatorContext constraintValidatorContext) {
        String[] date = birthDate.split("-");
        return checkMonth(Integer.parseInt(date[1].replace("0", "")));
    }

    private boolean checkMonth(int month){
        if(month < 1 || month > 12){
            return false;
        }
        return true;
    }
}
