package com.example.book_catalogue.dao;

import com.example.book_catalogue.entity.AuthorEntity;
import com.example.book_catalogue.entity.BookAuthorEntity;
import com.example.book_catalogue.entity.BookEntity;

import java.util.List;

public interface BookAuthorDao {
    void insertBookAuthor(BookAuthorEntity bookAuthorEntity);

    void deleteByAuthor(AuthorEntity authorEntity);

    void deleteByBook(BookEntity bookEntity);

    List<BookAuthorEntity> getByAuthor(AuthorEntity authorEntity);

    List<BookAuthorEntity> getByBook(BookEntity bookEntity);
}
