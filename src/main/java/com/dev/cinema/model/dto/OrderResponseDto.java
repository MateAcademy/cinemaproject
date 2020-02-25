package com.dev.cinema.model.dto;

import com.dev.cinema.model.Ticket;
import java.util.List;
import lombok.Data;

/**
 * @author Sergey Klunniy
 */
@Data
public class OrderResponseDto {

    private List<Ticket> tickets;

    private String userEmail;
}
