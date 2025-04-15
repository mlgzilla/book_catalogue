package com.example.book_catalogue.dao.impl;

import com.example.book_catalogue.dao.BookAuthorDao;
import com.example.book_catalogue.entity.AuthorEntity;
import com.example.book_catalogue.entity.BookAuthorEntity;
import com.example.book_catalogue.entity.BookEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(bookAuthorEntity);
            transaction.commit();
        }
    }

    @Override
    public void deleteByAuthor(AuthorEntity authorEntity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete from BookAuthorEntity where authorEntity = :authorEntity")
                    .setParameter("authorEntity", authorEntity)
                    .executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void deleteByBook(BookEntity bookEntity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete from BookAuthorEntity where bookEntity = :bookEntity")
                    .setParameter("bookEntity", bookEntity)
                    .executeUpdate();
            transaction.commit();
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
