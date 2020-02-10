package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
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
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Order> query = session.createQuery("from Order where user =:user");
            query.setMaxResults(10);
            query.setParameter("user", user);
            List<Order> list = query.list();
            return list;
        } catch (Exception e) {
            throw new RuntimeException("Can't getOrderHistory from database", e);
        }
    }
}
