package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.Movie;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import lombok.NonNull;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Sergey Klunniy
 */
@Repository
public class MovieDaoImpl implements MovieDao {

    private static final Logger LOGGER = Logger.getLogger(MovieDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Movie add(@NonNull Movie movie) {
        Transaction transaction = null;
        try (final Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long itemId = (Long)session.save(movie);
            transaction.commit();
            movie.setId(itemId);
            LOGGER.info("Stored the movie = " + movie);
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("can't insert Movie entity", e);
        }
    }

    @Override
    public List<Movie> getAll() {
        try (final Session session = sessionFactory.openSession()) {
            CriteriaQuery<Movie> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Movie.class);
            criteriaQuery.from(Movie.class);
            LOGGER.info("we getAll movies");
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all movies", e);
        }
    }
}
