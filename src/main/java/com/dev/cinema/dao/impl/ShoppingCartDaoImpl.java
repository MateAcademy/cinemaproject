package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.ShoppingCartDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author Sergey Klunniy
 */
@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long itemId = (Long) session.save(shoppingCart);
            transaction.commit();
            shoppingCart.setId(itemId);
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("can't add shoppingCart ", e);
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ShoppingCart> criteria = builder.createQuery(ShoppingCart.class);
            Root<ShoppingCart> root = criteria.from(ShoppingCart.class);

            criteria.select(root);
            criteria.where(builder.equal(root.get("user"), user));
            ShoppingCart shoppingCart = session.createQuery(criteria).uniqueResult();
            return shoppingCart;
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("can't insert Shopping entity", e);
        }
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(shoppingCart);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("can't clear Shopping entity", e);
        }
    }
}
