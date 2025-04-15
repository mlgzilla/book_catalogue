package com.example.book_catalogue.dao;

import com.example.book_catalogue.entity.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    Optional<Long> countAuthors();

    Optional<AuthorEntity> insertAuthor(AuthorEntity author);

    Optional<AuthorEntity> updateAuthor(Long id, String name, String biography);

    void deleteAuthor(AuthorEntity author);

    Optional<AuthorEntity> getAuthorById(Long id);

    List<AuthorEntity> getAuthorsByName(String authorName);

    List<AuthorEntity> getAuthorsByPage(Integer pageNo, Integer pageSize);
}
