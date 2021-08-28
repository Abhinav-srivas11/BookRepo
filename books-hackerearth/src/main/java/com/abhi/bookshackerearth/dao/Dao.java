package com.abhi.bookshackerearth.dao;

import com.abhi.bookshackerearth.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Component
@Repository
public class Dao {

    SessionFactory factory;
    Session session;
    public Dao() {
         factory = new Configuration().addAnnotatedClass(Book.class).configure("hibernate.cfg.xml").buildSessionFactory();

        session = factory.getCurrentSession();
    }


    public void saveIt(Book objectBook) {
        factory = new Configuration().addAnnotatedClass(Book.class).configure("hibernate.cfg.xml").buildSessionFactory();

        session = factory.getCurrentSession();
//        SessionFactory factory = new Configuration().addAnnotatedClass(com.abhi.entity.Book.class).configure("hibernate.cfg.xml").buildSessionFactory();
//
//        Session session = factory.getCurrentSession();
        System.out.println("Is FACTORY OPEN????" + factory.isOpen());
        System.out.println("Is SESSION OPEN????" + session.isOpen());
        try {
            session.beginTransaction();
            session.save(objectBook);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }

    public Book getIt(int bookID) {
        factory = new Configuration().addAnnotatedClass(Book.class).configure("hibernate.cfg.xml").buildSessionFactory();
        session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            Query qp = session.createQuery(" from book where bookID=:i", Book.class);
            qp.setParameter("i", bookID);
            return (Book) qp.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
        return null;
    }
}
