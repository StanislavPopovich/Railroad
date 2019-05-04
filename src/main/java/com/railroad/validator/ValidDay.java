package com.railroad.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({ FIELD, METHOD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DayValidator.class)
@Documented
public @interface ValidDay {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
