package com.dev.cinema.model.dto;

import lombok.Data;

/**
 * @author Sergey Klunniy
 */
@Data
public class MovieSessionRequestDto {

    private Long movieId;
    private Long cinemaHallId;
    private String showTime;
}
