package com.dev.cinema.dao;

import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.CinemaHall;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface CinemaHallDao {

    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll() throws DataProcessingException;
}
