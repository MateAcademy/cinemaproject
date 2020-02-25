package com.dev.cinema.service;

import com.dev.cinema.model.CinemaHall;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface CinemaHallService {

    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();

    CinemaHall getByIdCinemaHall(Long id);
}
