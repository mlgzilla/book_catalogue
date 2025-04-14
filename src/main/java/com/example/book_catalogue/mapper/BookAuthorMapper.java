package com.example.book_catalogue.mapper;

import com.example.book_catalogue.entity.AuthorEntity;
import com.example.book_catalogue.entity.BookAuthorEntity;
import com.example.book_catalogue.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class BookAuthorMapper {

    @Mapping(target = "id", ignore = true)
    public abstract BookAuthorEntity fromArguments(BookEntity bookEntity, AuthorEntity authorEntity);
}
