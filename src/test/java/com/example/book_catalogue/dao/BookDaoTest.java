package com.example.book_catalogue.dao;


import com.example.book_catalogue.BookCatalogueApplicationTests;
import com.example.book_catalogue.entity.BookEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.List;

public class BookDaoTest extends BookCatalogueApplicationTests {
    @Autowired
    private BookDao bookDao;

    private BookEntity book;

    @BeforeEach
    void init() {
        book = BookEntity.builder()
                .name("По ком звонит колокол")
                .description("возьму трубку, колокол звонит")
                .viewCounter(0L)
                .dateAdded(Instant.now())
                .build();
    }

//    @Test
//    void testInsertBook() {
//        bookDao.insertBook(book);
//    }

    @Test
    void testGetAllBooks() {
        List<BookEntity> allBooks = bookDao.getAllBooks(1, 2);
        System.out.println(allBooks);
    }

    @Test
    void testFindById() {
        BookEntity bookById = bookDao.getBookById(2L).orElseThrow();
        System.out.println(bookById);
    }
}
