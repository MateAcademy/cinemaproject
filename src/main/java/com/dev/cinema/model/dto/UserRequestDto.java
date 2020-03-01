package com.dev.cinema.model.dto;

import com.dev.cinema.annotations.email.EmailConstraint;
import com.dev.cinema.annotations.password.PasswordValuesMatch;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @author Sergey Klunniy
 */
@Data
@PasswordValuesMatch
public class UserRequestDto {

    @NotNull
    @EmailConstraint
    private String email;

    @NotNull
    @Size(min = 4, max = 5)
    private String password;

    @NotNull
    @Size(min = 4, max = 5)
    private String repeatPassword;
}
