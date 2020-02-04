package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {

    private static final Logger LOGGER = Logger.getLogger(MovieSessionDaoImpl.class);

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(movieSession);
            transaction.commit();
            movieSession.setId(id);
            return movieSession;
        } catch (Exception e) {
            if (transaction == null) {
                transaction.rollback();
            }
            LOGGER.error("Cannot add movie session to database", e);
            throw new RuntimeException();
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate showTime)
            throws DataProcessingException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.get(MovieSession.class, movieId);

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> cq = cb.createQuery(MovieSession.class);// результат + вказівник
            Root<MovieSession> root = cq.from(MovieSession.class); //звернення до таблиці
            Predicate predicateId = cb.equal(root.get("movie"), movieId); // WHERE moviessession.movie = movieId
            Predicate predicateDate = cb.between(root.get("showTime"),// WHERE moviessession.showTime = showTime
                    showTime.atStartOfDay(), showTime.plusDays(1).atStartOfDay());
            cq.select(root).where(cb.and(predicateId, predicateDate)); //+
            return session.createQuery(cq).getResultList();
        } catch (Exception e) {
            LOGGER.error("Cannot show all movie sessions from database");
            throw new DataProcessingException("Cannot show all movie sessions from database", e);
        }
    }
}