package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Sergey Klunniy
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Order create(Order order) {
        Transaction transaction = null;
        try (final Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(order);
            transaction.commit();
            order.setId(id);
            return order;
        } catch (Exception e) {
            if (transaction == null) {
                transaction.rollback();
            }
            throw new RuntimeException("Cannot add order to database", e);
        }
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
            Root<Order> root = criteria.from(Order.class);
            root.fetch("tickets", JoinType.LEFT);
            criteria.select(root).where(builder.equal(root.get("user"), user));
            return session.createQuery(criteria).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Couldn't show orders of " + user, e);
        }
    }
}
