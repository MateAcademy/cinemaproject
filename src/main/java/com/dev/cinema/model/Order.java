package com.dev.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany
    private List<Ticket> tickets;

    @ManyToOne
    private User user;

    private LocalDateTime showTime;

    public Order(List<Ticket> tickets, User user) {
        this.tickets = tickets;
        this.user = user;
        showTime = LocalDateTime.now();
    }
}
