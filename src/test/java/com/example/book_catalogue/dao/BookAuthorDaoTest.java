package com.example.book_catalogue.dao;

import com.example.book_catalogue.BookCatalogueApplicationTests;
import com.example.book_catalogue.entity.BookAuthorEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class BookAuthorDaoTest extends BookCatalogueApplicationTests {
    @Autowired
    private BookAuthorDao bookAuthorDao;

    private BookAuthorEntity bookAuthorEntity;


}
