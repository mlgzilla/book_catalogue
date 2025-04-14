package com.example.book_catalogue.dao;

import com.example.book_catalogue.BookCatalogueApplicationTests;
import com.example.book_catalogue.entity.AuthorEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AuthorDaoTest extends BookCatalogueApplicationTests {
    @Autowired
    private AuthorDao authorDao;

    private AuthorEntity author;

    @BeforeEach
    void init() {
        author = AuthorEntity.builder()
                .name("Братья Стругацкие")
                .biography("братухи")
                .build();
    }

//    @Test
//    void testInsertAuthor() {
//        authorDao.insertAuthor(author);
//    }

    @Test
    void testGetAllAuthors() {
        List<AuthorEntity> allBooks = authorDao.getAllAuthors(1, 2);
        System.out.println(allBooks);
    }

    @Test
    void testFindById() {
        AuthorEntity authorById = authorDao.getAuthorById(2L);
        System.out.println(authorById);
    }
}
