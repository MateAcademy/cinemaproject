package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.TicketDao;
import com.dev.cinema.model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Sergey Klunniy
 */
@Repository
public class TicketDaoImpl implements TicketDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction;
        try (final Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(ticket);
            transaction.commit();
            ticket.setId(id);
            return ticket;
        } catch (Exception e) {
            throw new RuntimeException("can't add user entity", e);
        }
    }
}
