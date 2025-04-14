package com.example.book_catalogue.dao.impl;

import com.example.book_catalogue.dao.BookDao;
import com.example.book_catalogue.entity.BookAuthorEntity;
import com.example.book_catalogue.entity.BookEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Order;
import org.hibernate.query.SelectionQuery;
import org.hibernate.query.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {

    private final SessionFactory sessionFactory;

    public BookDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<BookEntity> insertBook(BookEntity book) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
            return Optional.ofNullable(book);
        }
    }

    @Override
    public void updateBook(Long id, String name, String description) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            BookEntity bookEntity = session.get(BookEntity.class, id);
            bookEntity.setName(name);
            bookEntity.setName(description);
            session.merge(bookEntity);
            transaction.commit();
        }
    }

    @Override
    public void deleteBook(BookEntity book) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(book);
            transaction.commit();
        }
    }

    @Override
    public Optional<BookEntity> getBookById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            BookEntity bookEntity = session.get(BookEntity.class, id);
            bookEntity.setViewCounter(bookEntity.getViewCounter() + 1);
            session.merge(bookEntity);
            transaction.commit();
            return Optional.of(bookEntity);
        }
    }

    @Override
    public List<BookEntity> getBookByName(String bookName) {
        try (Session session = sessionFactory.openSession()) {
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            JpaCriteriaQuery<BookEntity> criteriaQuery = criteriaBuilder.createQuery(BookEntity.class);
            JpaRoot<BookEntity> book = criteriaQuery.from(BookEntity.class);
            JpaPredicate likePredicate = criteriaBuilder.like(book.get("name"), "%" + bookName + "%");
            criteriaQuery.select(book)
                    .where(likePredicate)
                    .orderBy(criteriaBuilder.asc(book.get("name")));
            return session.createSelectionQuery(criteriaQuery).getResultList();
        }
    }

    @Override
    public List<BookEntity> getAllBooks(Integer pageNo, Integer pageSize) {
        try (Session session = sessionFactory.openSession()) {
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            JpaCriteriaQuery<BookEntity> criteriaQuery = criteriaBuilder.createQuery(BookEntity.class);
            criteriaQuery.from(BookEntity.class);
            SelectionQuery<BookEntity> selectionQuery = session.createSelectionQuery(criteriaQuery);
            selectionQuery.setReadOnly(true);
            selectionQuery.setOrder(Collections.singletonList(Order.asc(BookEntity.class, "name")));
            selectionQuery.setFirstResult((pageNo - 1) * pageSize);
            selectionQuery.setMaxResults(pageSize);
            return selectionQuery.getResultList();
        }
    }
}
