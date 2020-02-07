package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.util.HibernateUtil;
import lombok.NonNull;
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

    @Override
    public MovieSession add(@NonNull MovieSession movieSession) {
        Transaction transaction = null;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(movieSession);
            transaction.commit();
            movieSession.setId(id);
            return movieSession;
        } catch (Exception e) {
            if (transaction == null) {
                transaction.rollback();
            }
            throw new RuntimeException("Cannot add movie session to database", e);
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(@NonNull Long movieId,@NonNull LocalDate showTime)
            throws DataProcessingException {
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
//            session.get(MovieSession.class, movieId);

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> cq = cb.createQuery(MovieSession.class);
            Root<MovieSession> root = cq.from(MovieSession.class);
            Predicate predicateId = cb.equal(root.get("movie"), movieId);
            Predicate predicateDate = cb.between(root.get("showTime"),
                    showTime.atStartOfDay(), showTime.plusDays(1).atStartOfDay());
            cq.select(root).where(cb.and(predicateId, predicateDate));
            return session.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Cannot show all movie sessions from database", e);
        }
    }
}
