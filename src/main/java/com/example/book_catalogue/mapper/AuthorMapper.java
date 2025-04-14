package com.example.book_catalogue.mapper;

import com.example.book_catalogue.entity.AuthorEntity;
import com.example.book_catalogue.model.AuthorRequestDto;
import com.example.book_catalogue.model.AuthorResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AuthorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bookAuthorEntities", ignore = true)
    public abstract AuthorEntity fromAuthorRequest(AuthorRequestDto authorRequestDto);

    public abstract AuthorResponseDto toAuthorResponse(AuthorEntity authorEntity);

    public abstract List<AuthorResponseDto> toAuthorResponseList(List<AuthorEntity> authorEntity);
}
