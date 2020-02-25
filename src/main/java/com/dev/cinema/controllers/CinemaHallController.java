package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.CinemaHallRequestDto;
import com.dev.cinema.model.dto.CinemaHallResponseDto;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.CinemaHallService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sergey Klunniy
 */
@RestController
@RequestMapping("/cinemaHall")
public class CinemaHallController {

    @Autowired
    private CinemaHallService cinemaHallService;

    @PostMapping("/cinemaHalls")
    public String addCinemaHall(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        cinemaHallService.add(cinemaHall);
        return "create one cinema-halls: " + cinemaHall;
    }

    @GetMapping("/cinemaHalls")
    public String getAllCinemaHall() {
        List<CinemaHall> allCinemaHalls = cinemaHallService.getAll();
        List<CinemaHallResponseDto> cinemaHallResponseDto = new ArrayList<>();
        for (CinemaHall cinemaHall : allCinemaHalls) {
            CinemaHallResponseDto dto = new CinemaHallResponseDto();
            dto.setCapacity(cinemaHall.getCapacity());
            dto.setDescription(cinemaHall.getDescription());
            cinemaHallResponseDto.add(dto);
        }
        return "All list cinemaHalls: " + cinemaHallResponseDto;
    }
}
