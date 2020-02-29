package com.dev.cinema.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sergey Klunniy
 */
@Data
@NoArgsConstructor
public class OrderRequestDto {

    private Long userId;

    private String movieTitle;

    private String userEmail;

    public OrderRequestDto(String movieTitle, String userEmail) {
        this.movieTitle = movieTitle;
        this.userEmail = userEmail;
    }
}
