package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HibernateUtil;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Sergey Klunniy
 */
@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        Transaction transaction = null;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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

