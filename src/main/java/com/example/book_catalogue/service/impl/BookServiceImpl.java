package com.example.book_catalogue.service.impl;

import com.example.book_catalogue.dao.AuthorDao;
import com.example.book_catalogue.dao.BookAuthorDao;
import com.example.book_catalogue.dao.BookDao;
import com.example.book_catalogue.entity.AuthorEntity;
import com.example.book_catalogue.entity.BookAuthorEntity;
import com.example.book_catalogue.entity.BookEntity;
import com.example.book_catalogue.exception.supplier.ExceptionSupplier;
import com.example.book_catalogue.mapper.AuthorMapper;
import com.example.book_catalogue.mapper.BookAuthorMapper;
import com.example.book_catalogue.mapper.BookMapper;
import com.example.book_catalogue.model.*;
import com.example.book_catalogue.service.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final BookAuthorDao bookAuthorDao;
    private final BookMapper bookMapper;
    private final BookAuthorMapper bookAuthorMapper;
    private final AuthorMapper authorMapper;
    private final ExceptionSupplier exceptionSupplier;

    @Override
    @Transactional
    public BookWithAuthorsResponseDto createBook(BookRequestDto bookRequestDto) {
        List<Long> authorsId = bookRequestDto.getAuthorId();
        List<AuthorEntity> authorEntities = authorsId.stream()
                .map(authorId -> authorDao.getAuthorById(authorId)
                        .orElseThrow(exceptionSupplier.authorNotFound(authorId)))
                .toList();
        BookEntity bookEntity = bookDao.insertBook(bookMapper.fromBookRequest(bookRequestDto))
                .orElseThrow(exceptionSupplier.bookCreationError());
        authorEntities.forEach(authorEntity -> bookAuthorDao
                .insertBookAuthor(bookAuthorMapper.fromArguments(bookEntity, authorEntity)));
        List<AuthorResponseDto> authorResponse = authorMapper.toAuthorResponseList(authorEntities);
        return bookMapper.toBookWithAuthorsResponse(bookEntity, authorResponse);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        BookEntity book = bookDao.getBookById(id)
                .orElseThrow(exceptionSupplier.bookNotFound(id));
        book.getBookAuthorEntities().clear();
        bookAuthorDao.deleteByBook(book);
        bookDao.deleteBook(book);
    }

    @Override
    @Transactional
    public BookWithAuthorsResponseDto getBookById(Long id) {
        BookEntity bookById = bookDao.getBookById(id)
                .orElseThrow(exceptionSupplier.bookNotFound(id));
        List<AuthorEntity> authorEntities = bookById.getBookAuthorEntities().stream()
                .map(BookAuthorEntity::getAuthorEntity)
                .toList();
        List<AuthorResponseDto> authorResponse = authorMapper.toAuthorResponseList(authorEntities);
        return bookMapper.toBookWithAuthorsResponse(bookById, authorResponse);
    }

    @Override
    @Transactional
    public List<BookResponseDto> getBooksByName(String name) {
        List<BookEntity> booksByName = bookDao.getBookByName(name);
        if (booksByName.isEmpty()) {
            return List.of();
        }
        return bookMapper.toBookResponseList(booksByName);
    }

    @Override
    @Transactional
    public Long getBooksCount() {
        return bookDao.countBooks()
                .orElseThrow(exceptionSupplier.bookCountError());
    }

    @Override
    @Transactional
    public List<BookResponseDto> getBooksByPage(PageRequestDto pageRequestDto) {
        List<BookEntity> booksByPage = bookDao.getAllBooks(pageRequestDto.getPageNum(), pageRequestDto.getPageSize());
        if (booksByPage.isEmpty()) {
            return List.of();
        }
        return bookMapper.toBookResponseList(booksByPage);
    }

    @Override
    @Transactional
    public List<BookResponseDto> getRecentBooks() {
        List<BookEntity> recentBooks = bookDao.getRecentBooks();
        if (recentBooks.isEmpty()) {
            return List.of();
        }
        return bookMapper.toBookResponseList(recentBooks);
    }
}
