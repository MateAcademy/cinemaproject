package com.dev.cinema.dao.hibernate;

import com.dev.cinema.dao.MovieDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.Movie;
import com.dev.cinema.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collections;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class MovieDaoImpl implements MovieDao {

    private static final Logger logger = Logger.getLogger(MovieDaoImpl.class);

    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long itemId = (Long)session.save(movie);
            transaction.commit();
            movie.setId(itemId);
            logger.info("Stored the movie = " + movie);
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't save user", e);
            throw new RuntimeException("can't insert Movie entity", e);
        }
    }

    @Override
    public List<Movie> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<Movie> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Movie.class);
            criteriaQuery.from(Movie.class);
            logger.info("we getAll movies");
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            logger.error("Can't save user", e);
            throw new DataProcessingException("Error retrieving all movies", e);
        }
    }
}
