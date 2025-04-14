package com.example.book_catalogue.service;

import com.example.book_catalogue.model.AuthorRequestDto;
import com.example.book_catalogue.model.AuthorResponseDto;
import com.example.book_catalogue.model.AuthorWithBooksResponseDto;

import java.util.List;

public interface AuthorService {

    AuthorResponseDto createAuthor(AuthorRequestDto authorRequestDto);

    void deleteAuthor(Long id);

    AuthorWithBooksResponseDto getAuthorById(Long id);

    List<AuthorResponseDto> getAuthorsByName(String name);

    List<AuthorResponseDto> getAuthorsByPage(Integer pageNumber);

    AuthorResponseDto updateAuthor(AuthorRequestDto authorRequestDto);
}
