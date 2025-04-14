package com.example.book_catalogue.service.impl;

import com.example.book_catalogue.dao.AuthorDao;
import com.example.book_catalogue.dao.BookAuthorDao;
import com.example.book_catalogue.dao.BookDao;
import com.example.book_catalogue.entity.AuthorEntity;
import com.example.book_catalogue.entity.BookAuthorEntity;
import com.example.book_catalogue.entity.BookEntity;
import com.example.book_catalogue.mapper.AuthorMapper;
import com.example.book_catalogue.mapper.BookAuthorMapper;
import com.example.book_catalogue.mapper.BookMapper;
import com.example.book_catalogue.model.AuthorResponseDto;
import com.example.book_catalogue.model.BookRequestDto;
import com.example.book_catalogue.model.BookResponseDto;
import com.example.book_catalogue.model.BookWithAuthorsResponseDto;
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

    @Override
    @Transactional
    public BookWithAuthorsResponseDto createBook(BookRequestDto bookRequestDto) {
        List<Long> authorsId = bookRequestDto.getAuthorId();
        List<AuthorEntity> authorEntities = authorsId.stream()
                .map(authorDao::getAuthorById)
                .toList();
        BookEntity bookEntity = bookDao.insertBook(bookMapper.fromBookRequest(bookRequestDto))
                .orElseThrow();
        authorEntities.forEach(authorEntity -> bookAuthorDao
                .insertBookAuthor(bookAuthorMapper.fromArguments(bookEntity, authorEntity)));
        List<AuthorResponseDto> authorResponse = authorMapper.toAuthorResponseList(authorEntities);
        return bookMapper.toBookWithAuthorsResponse(bookEntity, authorResponse);
    }

    @Override
    public void deleteBook(Long id) {
        BookEntity book = bookDao.getBookById(id)
                .orElseThrow();
        bookDao.deleteBook(book);
    }

    @Override
    public BookWithAuthorsResponseDto getBookById(Long id) {
        BookEntity bookById = bookDao.getBookById(id)
                .orElseThrow();
        List<AuthorEntity> authorEntities = bookById.getBookAuthorEntities().stream()
                .map(BookAuthorEntity::getAuthorEntity)
                .toList();
        List<AuthorResponseDto> authorResponse = authorMapper.toAuthorResponseList(authorEntities);
        return bookMapper.toBookWithAuthorsResponse(bookById, authorResponse);
    }

    @Override
    public List<BookResponseDto> getBooksByName(String name) {
        List<BookEntity> bookByName = bookDao.getBookByName(name);
        return List.of();
    }

    @Override
    public List<BookResponseDto> getBooksByPage(Integer pageNumber) {
        return List.of();
    }

    @Override
    public List<BookResponseDto> getRecentBooks() {
        return List.of();
    }

    @Override
    public BookWithAuthorsResponseDto updateBook(BookRequestDto bookRequestDto) {
        return null;
    }
}
