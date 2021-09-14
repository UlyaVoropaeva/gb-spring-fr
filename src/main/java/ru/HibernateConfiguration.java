package ru;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@Configuration
public class HibernateConfiguration {

    @Bean
    public EntityManagerFactory emFactory() {
        return new org.hibernate.cfg.Configuration().configure("META-INF/hibernate.cfg.xml").buildSessionFactory();

    }


    @Bean
    public EntityManager entityManager() {
        return emFactory().createEntityManager();
    }
}
