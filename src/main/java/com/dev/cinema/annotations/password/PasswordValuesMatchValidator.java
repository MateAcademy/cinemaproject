package com.dev.cinema.annotations.password;

import com.dev.cinema.model.dto.UserRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Sergey Klunniy
 */
public class PasswordValuesMatchValidator implements
        ConstraintValidator<PasswordValuesMatch, UserRequestDto> {

    @Override
    public boolean isValid(UserRequestDto userRegistrationDto, ConstraintValidatorContext
            constraintValidatorContext) {
        return userRegistrationDto.getPassword().equals(userRegistrationDto.getRepeatPassword());
    }
}
