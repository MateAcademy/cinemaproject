package com.dev.cinema.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author Sergey Klunniy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String firstName;

    @NonNull
    private String email;
}
