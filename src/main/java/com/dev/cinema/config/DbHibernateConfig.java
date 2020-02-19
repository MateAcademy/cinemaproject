package com.dev.cinema.config;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = {
    @ComponentScan("com.dev.cinema.dao.impl"),
    @ComponentScan("com.dev.cinema.service.impl"),
    @ComponentScan("com.dev.cinema.util")
})

public class DbHibernateConfig {

  @Autowired
  private Environment env;

  @Bean
  public LocalSessionFactoryBean getSessionFactory() {
    LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

    Properties properties = new Properties();
    properties.put(DRIVER, env.getProperty("mysql.driver"));
    properties.put(URL, env.getProperty("mysql.url"));
    properties.put(USER, env.getProperty("mysql.user"));
    properties.put(PASS, env.getProperty("mysql.password"));
    properties.put(DIALECT, env.getProperty("hibernate.dialect"));
    properties.put(QUERY_CACHE_FACTORY, env.getProperty("hibernate.dialect"));

    properties.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
    properties.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));

    properties.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3po.min_size"));
    properties.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3po.max_size"));
    properties.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3po.acquire_increment"));
    properties.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3po.timeout"));
    properties.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3po.max_statements"));

    factoryBean.setHibernateProperties(properties);
    factoryBean.setAnnotatedClasses(User.class, Ticket.class, ShoppingCart.class,
            Order.class, MovieSession.class, Movie.class, CinemaHall.class);
    return factoryBean;
  }

  @Bean
  public HibernateTransactionManager getTransactionManager() {
    HibernateTransactionManager manager = new HibernateTransactionManager();
    manager.setSessionFactory(getSessionFactory().getObject());
    return manager;
  }

}
