package com.example.book_catalogue.dao.impl;

import com.example.book_catalogue.dao.BookAuthorDao;
import com.example.book_catalogue.entity.AuthorEntity;
import com.example.book_catalogue.entity.BookAuthorEntity;
import com.example.book_catalogue.entity.BookEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookAuthorDaoImpl implements BookAuthorDao {

    private final SessionFactory sessionFactory;

    public BookAuthorDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insertBookAuthor(BookAuthorEntity bookAuthorEntity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(bookAuthorEntity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && (transaction.getStatus() == TransactionStatus.ACTIVE || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void deleteByAuthor(AuthorEntity authorEntity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("delete from BookAuthorEntity where authorEntity = :authorEntity")
                    .setParameter("authorEntity", authorEntity)
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && (transaction.getStatus() == TransactionStatus.ACTIVE || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void deleteByBook(BookEntity bookEntity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("delete from BookAuthorEntity where bookEntity = :bookEntity")
                    .setParameter("bookEntity", bookEntity)
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && (transaction.getStatus() == TransactionStatus.ACTIVE || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<BookAuthorEntity> getByAuthor(AuthorEntity authorEntity) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select BookAuthorEntity where authorEntity = :authorEntity", BookAuthorEntity.class)
                    .setParameter("authorEntity", authorEntity)
                    .getResultList();
        }
    }

    @Override
    public List<BookAuthorEntity> getByBook(BookEntity bookEntity) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select BookAuthorEntity where bookEntity = :bookEntity", BookAuthorEntity.class)
                    .setParameter("bookEntity", bookEntity)
                    .getResultList();
        }
    }
}
