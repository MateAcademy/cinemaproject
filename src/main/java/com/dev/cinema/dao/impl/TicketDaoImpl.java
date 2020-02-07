package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.TicketDao;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.util.HibernateUtil;
import org.hibernate.Session;

/**
 * @author Sergey Klunniy
 */
@Dao
public class TicketDaoImpl implements TicketDao {
    @Override
    public Ticket add(Ticket ticket) {

        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(ticket);
            session.getTransaction().commit();
            return ticket;
        } catch (Exception e) {
            throw new RuntimeException("can't add user entity", e);
        }
    }
}
