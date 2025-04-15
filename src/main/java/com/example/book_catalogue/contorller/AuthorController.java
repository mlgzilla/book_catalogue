package com.example.book_catalogue.contorller;

import com.example.book_catalogue.AuthorApi;
import com.example.book_catalogue.model.*;
import com.example.book_catalogue.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController implements AuthorApi {

    private final AuthorService authorService;

    @Override
    public ResponseEntity<AuthorResponseDto> createAuthor(AuthorRequestDto authorRequestDto) {
        AuthorResponseDto author = authorService.createAuthor(authorRequestDto);
        return ResponseEntity.ok(author);
    }

    @Override
    public ResponseEntity<Void> deleteAuthor(Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<AuthorWithBooksResponseDto> getAuthorById(Long id) {
        AuthorWithBooksResponseDto authorById = authorService.getAuthorById(id);
        return ResponseEntity.ok(authorById);
    }

    @Override
    public ResponseEntity<List<AuthorResponseDto>> getAuthorsByName(String name) {
        List<AuthorResponseDto> authorsByName = authorService.getAuthorsByName(name);
        return ResponseEntity.ok(authorsByName);
    }

    @Override
    public ResponseEntity<Long> getAuthorsCount() {
        Long authorsCount = authorService.getAuthorsCount();
        return ResponseEntity.ok(authorsCount);
    }

    @Override
    public ResponseEntity<List<AuthorResponseDto>> getAuthorsByPage(PageRequestDto pageRequestDto) {
        List<AuthorResponseDto> authorsByPage = authorService.getAuthorsByPage(pageRequestDto);
        return ResponseEntity.ok(authorsByPage);
    }

    @Override
    public ResponseEntity<AuthorWithBooksResponseDto> updateAuthor(AuthorUpdateRequestDto authorUpdateRequestDto) {
        AuthorWithBooksResponseDto authorResponseDto = authorService.updateAuthor(authorUpdateRequestDto);
        return ResponseEntity.ok(authorResponseDto);
    }
}
