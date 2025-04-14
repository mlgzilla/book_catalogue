package com.example.book_catalogue.dao;

import com.example.book_catalogue.entity.AuthorEntity;

import java.util.List;

public interface AuthorDao {
    void insertAuthor(AuthorEntity author);

    void updateAuthor(Long id, String name, String biography);

    void deleteAuthor(AuthorEntity author);

    AuthorEntity getAuthorById(Long id);

    List<AuthorEntity> getAllAuthors(Integer pageNo, Integer pageSize);
}
