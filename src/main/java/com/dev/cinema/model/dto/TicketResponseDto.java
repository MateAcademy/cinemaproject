package com.dev.cinema.model.dto;

import lombok.Data;

/**
 * @author Sergey Klunniy
 */
@Data
public class TicketResponseDto {

    private String movieTitle;
    private Long cinemaHallId;
    private String userEmail;
    private String showTime;
}
