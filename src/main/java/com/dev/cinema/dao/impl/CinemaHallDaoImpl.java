package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.CinemHallDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Sergey Klunniy
 */
@Dao
public class CinemaHallDaoImpl implements CinemHallDao {

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(cinemaHall);
            transaction.commit();
            cinemaHall.setId(id);
            return cinemaHall;
        } catch (Exception e) {
            if (transaction == null) {
                transaction.rollback();
            }
            throw new RuntimeException("Cannot add cinema hall to database", e);
        }
    }

    @Override
    public List<CinemaHall> getAll() throws DataProcessingException {
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CinemaHall> criteriaQuery = criteriaBuilder.createQuery(CinemaHall.class);
            criteriaQuery.from(CinemaHall.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Cannot show all cinema halls from database", e);
        }
    }
}
