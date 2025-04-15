package com.example.book_catalogue.contorller;

import com.example.book_catalogue.BookApi;
import com.example.book_catalogue.model.BookRequestDto;
import com.example.book_catalogue.model.BookResponseDto;
import com.example.book_catalogue.model.BookWithAuthorsResponseDto;
import com.example.book_catalogue.model.PageRequestDto;
import com.example.book_catalogue.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController implements BookApi {

    private final BookService bookService;

    @Override
    public ResponseEntity<BookWithAuthorsResponseDto> createBook(BookRequestDto bookRequestDto) {
        BookWithAuthorsResponseDto createdBook = bookService.createBook(bookRequestDto);
        return ResponseEntity.ok(createdBook);
    }

    @Override
    public ResponseEntity<Void> deleteBook(Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<BookWithAuthorsResponseDto> getBookById(Long id) {
        BookWithAuthorsResponseDto bookById = bookService.getBookById(id);
        return ResponseEntity.ok(bookById);
    }

    @Override
    public ResponseEntity<List<BookResponseDto>> getBooksByName(String name) {
        List<BookResponseDto> booksByName = bookService.getBooksByName(name);
        return ResponseEntity.ok(booksByName);
    }

    @Override
    public ResponseEntity<Long> getBooksCount() {
        Long booksCount = bookService.getBooksCount();
        return ResponseEntity.ok(booksCount);
    }

    @Override
    public ResponseEntity<List<BookResponseDto>> getBooksByPage(PageRequestDto pageRequestDto) {
        List<BookResponseDto> booksByPage = bookService.getBooksByPage(pageRequestDto);
        return ResponseEntity.ok(booksByPage);
    }

    @Override
    public ResponseEntity<List<BookResponseDto>> getRecentBooks() {
        List<BookResponseDto> recentBooks = bookService.getRecentBooks();
        return ResponseEntity.ok(recentBooks);
    }
}
