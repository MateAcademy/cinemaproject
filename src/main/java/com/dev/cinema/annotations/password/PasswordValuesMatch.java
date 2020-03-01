package com.dev.cinema.annotations.password;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author Sergey Klunniy
 */
@Constraint(validatedBy = PasswordValuesMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValuesMatch {
    String message() default "Passwords don't match!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
