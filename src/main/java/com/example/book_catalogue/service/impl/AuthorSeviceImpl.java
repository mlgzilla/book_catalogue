package com.example.book_catalogue.service.impl;

import com.example.book_catalogue.dao.AuthorDao;
import com.example.book_catalogue.model.AuthorRequestDto;
import com.example.book_catalogue.model.AuthorResponseDto;
import com.example.book_catalogue.model.AuthorWithBooksResponseDto;
import com.example.book_catalogue.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorSeviceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public AuthorResponseDto createAuthor(AuthorRequestDto authorRequestDto) {
        return null;
    }

    @Override
    public void deleteAuthor(Long id) {

    }

    @Override
    public AuthorWithBooksResponseDto getAuthorById(Long id) {
        return null;
    }

    @Override
    public List<AuthorResponseDto> getAuthorsByName(String name) {
        return List.of();
    }

    @Override
    public List<AuthorResponseDto> getAuthorsByPage(Integer pageNumber) {
        return List.of();
    }

    @Override
    public AuthorResponseDto updateAuthor(AuthorRequestDto authorRequestDto) {
        return null;
    }
}
