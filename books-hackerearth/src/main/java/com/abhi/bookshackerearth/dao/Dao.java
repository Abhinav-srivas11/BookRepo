package com.abhi.bookshackerearth.dao;

import com.abhi.bookshackerearth.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Component
@Repository
public class Dao {

    SessionFactory factory; //its a factory of session (that is creates and provides session object
    //it provides factory method to get the object of session
    //its a client of ClientProvider
    //holds second level cache of data (optional)
//    ConnectionProvider is a factory of JDBC connections. It abstracts the application from DriverManager or DataSource.
    Session session;
    //it provides an interface between application and data stored in database
    //its short lived
    //it wraps the JDBC connection
    //its a factory of Transcation, Query and Criteria
    //holds first level cache of data (mandatory)
    //org.hibernate.Session provides methods to insert, update and delete objects.
    public Dao() {
        factory = new Configuration().addAnnotatedClass(Book.class).configure("hibernate.cfg.xml").buildSessionFactory();
        session = factory.getCurrentSession();
    }


    public void saveIt(Book objectBook) {
        factory = new Configuration().addAnnotatedClass(Book.class).configure("hibernate.cfg.xml").buildSessionFactory();

        session = factory.getCurrentSession();
        System.out.println("Is FACTORY OPEN? : " + factory.isOpen());
        System.out.println("Is SESSION OPEN? : " + session.isOpen());
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

    // this Dao method allows us to fuzzy search for the title of the book.
    //kind of like google
    public List fuzzySearch(String searchString) {
        factory = new Configuration().addAnnotatedClass(Book.class).configure("hibernate.cfg.xml").buildSessionFactory();
        session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            //after session is started, we run below query to check for the title
            Query qp = session.createQuery(" from book where title like ?0").setParameter(0, "%"+searchString+"%");
            System.err.println(qp.toString() + "IS THE QUERY STRING USED");
            return qp.getResultList(); //returns the result set
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
        return null; //null if anothing worked above
    }
}
