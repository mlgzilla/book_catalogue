package com.example.book_catalogue.service;

import com.example.book_catalogue.model.BookRequestDto;
import com.example.book_catalogue.model.BookResponseDto;
import com.example.book_catalogue.model.BookWithAuthorsResponseDto;

import java.util.List;

public interface BookService {

    BookWithAuthorsResponseDto createBook(BookRequestDto bookRequestDto);

    void deleteBook(Long id);

    BookWithAuthorsResponseDto getBookById(Long id);

    List<BookResponseDto> getBooksByName(String name);

    List<BookResponseDto> getBooksByPage(Integer pageNumber);

    List<BookResponseDto> getRecentBooks();

    BookWithAuthorsResponseDto updateBook(BookRequestDto bookRequestDto);
}
