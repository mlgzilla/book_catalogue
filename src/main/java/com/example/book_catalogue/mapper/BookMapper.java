package com.example.book_catalogue.mapper;

import com.example.book_catalogue.entity.BookEntity;
import com.example.book_catalogue.model.AuthorResponseDto;
import com.example.book_catalogue.model.BookRequestDto;
import com.example.book_catalogue.model.BookResponseDto;
import com.example.book_catalogue.model.BookWithAuthorsResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class BookMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateAdded", expression = "java(java.time.Instant.now())")
    @Mapping(target = "viewCounter", expression = "java(1L)")
    @Mapping(target = "bookAuthorEntities", ignore = true)
    public abstract BookEntity fromBookRequest(BookRequestDto bookRequestDto);

    public abstract BookResponseDto toBookResponse(BookEntity bookEntity);

    public abstract List<BookResponseDto> toBookResponseList(List<BookEntity> bookEntity);

    @Mapping(target = "bookResponse", expression = "java(toBookResponse(bookEntity))")
    public abstract BookWithAuthorsResponseDto toBookWithAuthorsResponse(BookEntity bookEntity, List<AuthorResponseDto> bookAuthors);
}
