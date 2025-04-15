package com.example.book_catalogue.service;

import com.example.book_catalogue.model.*;

import java.util.List;

public interface AuthorService {

    AuthorResponseDto createAuthor(AuthorRequestDto authorRequestDto);

    void deleteAuthor(Long id);

    AuthorWithBooksResponseDto getAuthorById(Long id);

    List<AuthorResponseDto> getAuthorsByName(String name);

    Long getAuthorsCount();

    List<AuthorResponseDto> getAuthorsByPage(PageRequestDto pageRequestDto);

    AuthorWithBooksResponseDto updateAuthor(AuthorUpdateRequestDto authorUpdateRequestDto);
}
