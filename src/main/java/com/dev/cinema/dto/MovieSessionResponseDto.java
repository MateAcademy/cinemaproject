package com.dev.cinema.dto;

import lombok.Data;

/**
 * @author Sergey Klunniy
 */
@Data
public class MovieSessionResponseDto {

    private Long movieSessionId;
    private Long cinemaHallId;
    private Long movieId;
    private String showTime;

}
