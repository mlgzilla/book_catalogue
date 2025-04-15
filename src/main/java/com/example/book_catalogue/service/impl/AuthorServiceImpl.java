package com.example.book_catalogue.service.impl;

import com.example.book_catalogue.dao.AuthorDao;
import com.example.book_catalogue.dao.BookAuthorDao;
import com.example.book_catalogue.dao.BookDao;
import com.example.book_catalogue.entity.AuthorEntity;
import com.example.book_catalogue.entity.BookAuthorEntity;
import com.example.book_catalogue.entity.BookEntity;
import com.example.book_catalogue.exception.supplier.ExceptionSupplier;
import com.example.book_catalogue.mapper.AuthorMapper;
import com.example.book_catalogue.mapper.BookMapper;
import com.example.book_catalogue.model.*;
import com.example.book_catalogue.service.AuthorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;
    private final BookAuthorDao bookAuthorDao;
    private final BookDao bookDao;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;
    private final ExceptionSupplier exceptionSupplier;

    @Override
    @Transactional
    public AuthorResponseDto createAuthor(AuthorRequestDto authorRequestDto) {
        AuthorEntity authorEntity = authorDao.insertAuthor(authorMapper.fromAuthorRequest(authorRequestDto))
                .orElseThrow(exceptionSupplier.authorCreationError());
        return authorMapper.toAuthorResponse(authorEntity);
    }

    @Override
    @Transactional
    public void deleteAuthor(Long id) {
        AuthorEntity authorEntity = authorDao.getAuthorById(id)
                .orElseThrow(exceptionSupplier.authorNotFound(id));
        List<BookEntity> bookEntities = authorEntity.getBookAuthorEntities().stream()
                .map(BookAuthorEntity::getBookEntity)
                .filter(bookEntity -> bookEntity.getBookAuthorEntities().size() == 1)
                .toList();
        bookEntities.forEach(bookEntity -> bookEntity.getBookAuthorEntities().clear());
        authorEntity.getBookAuthorEntities().clear();
        bookAuthorDao.deleteByAuthor(authorEntity);
        bookEntities.forEach(bookDao::deleteBook);
        authorDao.deleteAuthor(authorEntity);
    }

    @Override
    @Transactional
    public AuthorWithBooksResponseDto getAuthorById(Long id) {
        AuthorEntity authorEntity = authorDao.getAuthorById(id)
                .orElseThrow(exceptionSupplier.authorNotFound(id));
        List<BookResponseDto> bookEntities = authorEntity.getBookAuthorEntities().stream()
                .map(BookAuthorEntity::getBookEntity)
                .map(bookMapper::toBookResponse)
                .toList();
        return authorMapper.toAuthorWithBooksResponse(authorEntity, bookEntities);
    }

    @Override
    @Transactional
    public List<AuthorResponseDto> getAuthorsByName(String name) {
        List<AuthorEntity> authorsByName = authorDao.getAuthorsByName(name);
        if (authorsByName.isEmpty()) {
            return List.of();
        }
        return authorMapper.toAuthorResponseList(authorsByName);
    }

    @Override
    @Transactional
    public Long getAuthorsCount() {
        return authorDao.countAuthors()
                .orElseThrow(exceptionSupplier.authorCountError());
    }

    @Override
    @Transactional
    public List<AuthorResponseDto> getAuthorsByPage(PageRequestDto pageRequestDto) {
        List<AuthorEntity> authorsByPage = authorDao.getAuthorsByPage(pageRequestDto.getPageNum(), pageRequestDto.getPageSize());
        if (authorsByPage.isEmpty()) {
            return List.of();
        }
        return authorMapper.toAuthorResponseList(authorsByPage);
    }

    @Override
    @Transactional
    public AuthorWithBooksResponseDto updateAuthor(AuthorUpdateRequestDto authorUpdateRequestDto) {
        AuthorEntity updatedtAuthorEntity = authorDao.updateAuthor(authorUpdateRequestDto.getId(),
                        authorUpdateRequestDto.getName(),
                        authorUpdateRequestDto.getBiography())
                .orElseThrow(exceptionSupplier.authorUpdateError());
        List<BookResponseDto> bookEntities = updatedtAuthorEntity.getBookAuthorEntities().stream()
                .map(BookAuthorEntity::getBookEntity)
                .map(bookMapper::toBookResponse)
                .toList();
        return authorMapper.toAuthorWithBooksResponse(updatedtAuthorEntity, bookEntities);
    }
}
