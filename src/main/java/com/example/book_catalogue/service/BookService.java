package com.example.book_catalogue.service;

import com.example.book_catalogue.model.BookRequestDto;
import com.example.book_catalogue.model.BookResponseDto;
import com.example.book_catalogue.model.BookWithAuthorsResponseDto;
import com.example.book_catalogue.model.PageRequestDto;

import java.util.List;

public interface BookService {

    BookWithAuthorsResponseDto createBook(BookRequestDto bookRequestDto);

    void deleteBook(Long id);

    BookWithAuthorsResponseDto getBookById(Long id);

    List<BookResponseDto> getBooksByName(String name);

    Long getBooksCount();

    List<BookResponseDto> getBooksByPage(PageRequestDto pageRequestDto);

    List<BookResponseDto> getRecentBooks();
}
