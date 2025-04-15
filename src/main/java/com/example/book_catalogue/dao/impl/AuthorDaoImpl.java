package com.example.book_catalogue.dao.impl;

import com.example.book_catalogue.dao.AuthorDao;
import com.example.book_catalogue.entity.AuthorEntity;
import com.example.book_catalogue.entity.BookEntity;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.*;
import org.hibernate.query.Order;
import org.hibernate.query.SelectionQuery;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaPredicate;
import org.hibernate.query.criteria.JpaRoot;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private final SessionFactory sessionFactory;

    public AuthorDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<Long> countAuthors() {
        try (Session session = sessionFactory.openSession()) {
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            JpaCriteriaQuery<AuthorEntity> criteriaQuery = criteriaBuilder.createQuery(AuthorEntity.class);
            JpaRoot<AuthorEntity> author = criteriaQuery.from(AuthorEntity.class);
            criteriaQuery.select(author);
            return Optional.of(session.createSelectionQuery(criteriaQuery).getResultCount());
        } catch (HibernateException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AuthorEntity> insertAuthor(AuthorEntity author) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(author);
            transaction.commit();
            return Optional.of(author);
        } catch (HibernateException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AuthorEntity> updateAuthor(Long id, String name, String biography) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            AuthorEntity authorEntity = session.get(AuthorEntity.class, id);
            if (authorEntity == null) {
                return Optional.empty();
            }
            authorEntity.setName(name);
            authorEntity.setName(biography);
            session.merge(authorEntity);
            transaction.commit();
            return Optional.of(authorEntity);
        } catch (HibernateException e) {
            return Optional.empty();
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
    public Optional<AuthorEntity> getAuthorById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            AuthorEntity authorEntity = session.get(AuthorEntity.class, id, LockMode.READ);
            if (authorEntity == null) {
                return Optional.empty();
            }
            transaction.commit();
            return Optional.of(authorEntity);
        }
    }

    @Override
    public List<AuthorEntity> getAuthorsByName(String authorName) {
        try (Session session = sessionFactory.openSession()) {
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            JpaCriteriaQuery<AuthorEntity> criteriaQuery = criteriaBuilder.createQuery(AuthorEntity.class);
            JpaRoot<AuthorEntity> author = criteriaQuery.from(AuthorEntity.class);
            JpaPredicate likePredicate = criteriaBuilder.like(author.get("name"), "%" + authorName + "%");
            criteriaQuery.select(author)
                    .where(likePredicate)
                    .orderBy(criteriaBuilder.asc(author.get("name")));
            return session.createSelectionQuery(criteriaQuery).getResultList();
        }
    }

    @Override
    public List<AuthorEntity> getAuthorsByPage(Integer pageNo, Integer pageSize) {
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
