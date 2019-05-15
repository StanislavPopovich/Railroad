package com.railroad.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BirthDay implements ConstraintValidator<ValidBirthDay, String> {

    @Override
    public void initialize(final ValidBirthDay annotation) {

    }

    @Override
    public boolean isValid(final String birthDate, final ConstraintValidatorContext constraintValidatorContext) {
        String[] date = birthDate.split("-");
        int day = Integer.parseInt(date[2].replace("0",""));
        int month = Integer.parseInt(date[1].replace("0",""));
        int year = Integer.parseInt(date[0]);
        return checkDay(day, month, year);
    }

    private boolean checkDay(int day, int month, int year){
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
            if(day < 1 || day > 31){
                return false;
            }
        }else if(month == 2){
            if(isLeapYear(year)){
                if(day < 1 || day > 29){
                    return false;
                }
            }else{
                if(day < 1 || day > 28){
                    return false;
                }
            }
        }else {
            if(day < 1 || day > 30){
                return false;
            }
        }
        return true;
    }

    private boolean isLeapYear(int year){
        int[] leapYears = initLeapsYears();
        for(int i = 0; i < leapYears.length; i++){
            if(year == leapYears[i]){
                return true;
            }
        }
        return false;
    }

    private int[] initLeapsYears(){
        int[] leapYears = new int[(2100 - 1904)/4];
        int currentYear = 1904;
        int i = 0;
        while (currentYear < 2100){
            leapYears[i] = currentYear;
            currentYear += 4;
            i++;
        }
        return leapYears;
    }
}
