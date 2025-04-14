package com.example.book_catalogue.dao.impl;

import com.example.book_catalogue.dao.AuthorDao;
import com.example.book_catalogue.entity.AuthorEntity;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Order;
import org.hibernate.query.SelectionQuery;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private final SessionFactory sessionFactory;

    public AuthorDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insertAuthor(AuthorEntity author) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(author);
            transaction.commit();
        }
    }

    @Override
    public void updateAuthor(Long id, String name, String biography) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            AuthorEntity authorEntity = session.get(AuthorEntity.class, id);
            authorEntity.setName(name);
            authorEntity.setName(biography);
            session.merge(authorEntity);
            transaction.commit();
        }
    }

    @Override
    public void deleteAuthor(AuthorEntity author) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(author);
            transaction.commit();
        }
    }

    @Override
    public AuthorEntity getAuthorById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            AuthorEntity authorEntity = session.get(AuthorEntity.class, id);
            transaction.commit();
            return authorEntity;
        }
    }

    @Override
    public List<AuthorEntity> getAllAuthors(Integer pageNo, Integer pageSize) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<AuthorEntity> selectQuery = session.getCriteriaBuilder().createQuery(AuthorEntity.class);
            selectQuery.from(AuthorEntity.class);
            SelectionQuery<AuthorEntity> query = session.createQuery(selectQuery);
            query.setReadOnly(true);
            query.setOrder(Collections.singletonList(Order.asc(AuthorEntity.class, "name")));
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.list();
        }
    }
}
