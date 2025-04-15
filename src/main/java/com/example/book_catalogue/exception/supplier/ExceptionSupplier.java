package com.example.book_catalogue.exception.supplier;

import com.example.book_catalogue.exception.InternalServerErrorException;
import com.example.book_catalogue.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;


@Component
public class ExceptionSupplier {

    public Supplier<ResourceNotFoundException> authorNotFound(Long authorId) {
        return () -> new ResourceNotFoundException(String.format("Автор с id %s не найден", authorId));
    }

    public Supplier<InternalServerErrorException> authorCreationError() {
        return () -> new InternalServerErrorException("При создании карточки автора возникла ошибка");
    }

    public Supplier<InternalServerErrorException> authorUpdateError() {
        return () -> new InternalServerErrorException("При обновлении карточки автора возникла ошибка");
    }

    public Supplier<InternalServerErrorException> authorCountError() {
        return () -> new InternalServerErrorException("При получении количества карточек авторов возникла ошибка");
    }

    public Supplier<ResourceNotFoundException> bookNotFound(Long bookId) {
        return () -> new ResourceNotFoundException(String.format("Книга с id %s не найдена", bookId));
    }

    public Supplier<InternalServerErrorException> bookCreationError() {
        return () -> new InternalServerErrorException("При создании карточки книги возникла ошибка");
    }

    public Supplier<InternalServerErrorException> bookCountError() {
        return () -> new InternalServerErrorException("При получении количества карточек книг возникла ошибка");
    }
}
