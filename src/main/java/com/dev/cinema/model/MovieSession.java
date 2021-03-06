package com.dev.cinema.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sergey Klunniy
 */
@Data
@Entity
@NoArgsConstructor
public class MovieSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    private Movie movie;

    @ManyToOne()
    private CinemaHall cinemaHall;

    private LocalDateTime showTime;
}
