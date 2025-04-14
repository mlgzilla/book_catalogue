package com.example.book_catalogue.contorller;

import com.example.book_catalogue.AuthorApi;
import com.example.book_catalogue.model.AuthorRequestDto;
import com.example.book_catalogue.model.AuthorResponseDto;
import com.example.book_catalogue.model.AuthorWithBooksResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController implements AuthorApi {
    @Override
    public ResponseEntity<AuthorResponseDto> createAuthor(AuthorRequestDto authorRequestDto) {
        return AuthorApi.super.createAuthor(authorRequestDto);
    }

    @Override
    public ResponseEntity<Void> deleteAuthor(Long id) {
        return AuthorApi.super.deleteAuthor(id);
    }

    @Override
    public ResponseEntity<AuthorWithBooksResponseDto> getAuthorById(Long id) {
        return AuthorApi.super.getAuthorById(id);
    }

    @Override
    public ResponseEntity<List<AuthorResponseDto>> getAuthorsByName(String name) {
        return AuthorApi.super.getAuthorsByName(name);
    }

    @Override
    public ResponseEntity<List<AuthorResponseDto>> getAuthorsByPage(Integer pageNumber) {
        return AuthorApi.super.getAuthorsByPage(pageNumber);
    }

    @Override
    public ResponseEntity<AuthorResponseDto> updateAuthor(AuthorRequestDto authorRequestDto) {
        return AuthorApi.super.updateAuthor(authorRequestDto);
    }
}
