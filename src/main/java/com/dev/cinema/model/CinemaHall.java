package com.dev.cinema.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Sergey Klunniy
 */
@Data
@Entity
@Table(name = "cinema_hall")
public class CinemaHall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "description")
    private String description;
}
