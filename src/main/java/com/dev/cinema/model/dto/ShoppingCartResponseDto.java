package com.dev.cinema.model.dto;

import java.util.List;
import lombok.Data;

/**
 * @author Sergey Klunniy
 */
@Data
public class ShoppingCartResponseDto {

    private List<TicketResponseDto> tickets;
    private String userEmail;
}
