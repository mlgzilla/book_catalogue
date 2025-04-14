package com.example.book_catalogue.dao;


import com.example.book_catalogue.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Optional<BookEntity> insertBook(BookEntity book);

    void updateBook(Long id, String name, String description);

    void deleteBook(BookEntity book);

    Optional<BookEntity> getBookById(Long id);

    List<BookEntity> getBookByName(String bookName);

    List<BookEntity> getAllBooks(Integer pageNo, Integer pageSize);
}
